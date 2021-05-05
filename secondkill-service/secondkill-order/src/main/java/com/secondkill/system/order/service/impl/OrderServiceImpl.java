package com.secondkill.system.order.service.impl;

import com.secondkill.api.goods.feign.GoodsFeign;
import com.secondkill.api.goods.vo.MsGoodsDetailVO;
import com.secondkill.api.order.entry.TbOrder;
import com.secondkill.api.order.vo.ListOrderVO;
import com.secondkill.api.order.vo.ListUserOrderVO;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.RedisLuaUtils;
import com.secondkill.common.utils.Result;
import com.secondkill.common.utils.TimeUtils;
import com.secondkill.message.config.DelayQueueConfig;
import com.secondkill.message.config.StockQueueConfig;
import com.secondkill.system.order.mapper.OrderMapper;
import com.secondkill.system.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 订单service层实现类
 * @author Choy
 * @date 2021/03/28
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsFeign goodsFeign;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisLuaUtils redisLuaUtils;

    /**
     * 创建订单
     * @param msGoodsId
     * @param userId
     * @return
     */
    @Override
    public int addOrder(Integer msGoodsId, Integer userId) {
        Optional<Result<MsGoodsDetailVO>> feignMsGoods = Optional.ofNullable(goodsFeign.getMsGoods(msGoodsId));
        if (!feignMsGoods.isPresent()){
            return -1;
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderState(1);
        tbOrder.setCount(1);
        tbOrder.setCreateTime(new Date());
        tbOrder.setGoodsId(feignMsGoods.get().getData().getGoodsId());
        tbOrder.setGoodsTitle(feignMsGoods.get().getData().getGoodsTitle());
        tbOrder.setUserId(userId);
        tbOrder.setUnitPrice(new BigDecimal(feignMsGoods.get().getData().getMsPrice()));
        tbOrder.setMsGoodsId(msGoodsId);
        int affectRows = orderMapper.addOrder(tbOrder);
        if (affectRows > 0){
            // 将订单添加到延时队列中，等待商品服务消费扣库存
            rabbitTemplate.convertAndSend(DelayQueueConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME, tbOrder.getId());
            return tbOrder.getId();
        }
        return -1;
    }

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    @Override
    public TbOrder getOrderById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    /**
     * 支付订单，修改订单状态
     * @param orderId
     * @return
     */
    @Override
    public int payUpdateOrder(Integer orderId) {
        // 获取订单状态
        TbOrder msGoodsIdAndUserId = orderMapper.getMsGoodsIdAndUserId(orderId);
        // 判断订单状态是否为未支付
        if (msGoodsIdAndUserId.getOrderState() != 1){
            return msGoodsIdAndUserId.getOrderState();// 0 2
        }
        rabbitTemplate.convertAndSend(StockQueueConfig.STOCK_QUEUE_NAME, msGoodsIdAndUserId.getMsGoodsId());
        return orderMapper.payUpdateOrder(orderId, new Date());
    }


    /**
     * 分页列出所有订单
     * @param page
     * @param total
     * @return
     */
    @Override
    public List<TbOrder> listOrder(Integer page, Integer total) {
        return orderMapper.listOrder(page, total);
    }

    /**
     * 统计订单总数
     * @return
     */
    @Override
    public int countOrder() {
        return orderMapper.countOrder();
    }

    /**
     * 删除订单
     * @param ids
     * @return
     */
    @Override
    public int delOrder(Integer[] ids) {
        return orderMapper.delOrder(ids);
    }


    /**
     * 根据用户id获取该用户的订单列表
     * @param userId
     * @return
     */
    @Override
    public List<ListUserOrderVO> listOrderByUserId(Integer userId) {
        Optional<List<TbOrder>> optionalTbOrderList = Optional.ofNullable(orderMapper.listOrderByUserId(userId));
        if (!optionalTbOrderList.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        List<ListUserOrderVO> listUserOrderVOList = new ArrayList<>();
        for (TbOrder tbOrder : optionalTbOrderList.get()) {
            ListUserOrderVO listUserOrderVO = new ListUserOrderVO();
            BeanUtils.copyProperties(tbOrder, listUserOrderVO);
            listUserOrderVO.setUnitPrice(tbOrder.getUnitPrice().toString());
            listUserOrderVO.setCreateTime(TimeUtils.date2String(tbOrder.getCreateTime()));
            if (tbOrder.getPayTime() != null){
                listUserOrderVO.setPayTime(TimeUtils.date2String(tbOrder.getPayTime()));
            }
            listUserOrderVOList.add(listUserOrderVO);
        }
        return listUserOrderVOList;
    }


    /**
     * 处理订单未支付自动取消
     * @param orderId
     */
    @RabbitListener(queues = DelayQueueConfig.DELAY_PROCESS_QUEUE_NAME)
    @RabbitHandler
    public void updateOrderState(Integer orderId){
        try{
            TbOrder tbOrder = orderMapper.getMsGoodsIdAndUserId(orderId);
            if (tbOrder.getOrderState() == 1){
                logger.info("订单号为：{}的订单因超时支付自动取消!", orderId);
                // 库存回滚
                redisLuaUtils.restoreStock(tbOrder.getMsGoodsId(), tbOrder.getUserId());
                // 取消订单
                orderMapper.updateOrderState(orderId);
            }
            logger.info("订单号为：{}的订单不需要改变状态!", orderId);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }
}
