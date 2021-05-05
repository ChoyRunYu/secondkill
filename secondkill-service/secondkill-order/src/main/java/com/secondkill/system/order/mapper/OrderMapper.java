package com.secondkill.system.order.mapper;


import com.secondkill.api.order.entry.TbOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 订单mapper类
 * @author choy
 * @date 2021/03/28
 */
public interface OrderMapper {

    /**
     * 创建订单
     * @param tbOrder
     * @return
     */
    int addOrder(TbOrder tbOrder);

    /**
     * 订单超时未支付自动取消
     * @param orderId
     * @return
     */
    int updateOrderState(Integer orderId);

    /**
     * 获取秒杀id和用户id
     * @param orderId
     * @return
     */
    TbOrder getMsGoodsIdAndUserId(Integer orderId);


    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    TbOrder getOrderById(Integer orderId);

    /**
     * 支付订单，修改订单状态
     * @param orderId
     * @param payTime
     * @return
     */
    int payUpdateOrder(@Param("orderId")Integer orderId, @Param("payTime")Date payTime);

    /**
     * 分页列出所有描述订单
     * @return
     */
    List<TbOrder> listOrder(@Param("page")Integer page, @Param("total")Integer total);

    /**
     * 统计订单总数
     * @return
     */
    int countOrder();


    /**
     * 删除订单
     * @param orderIds
     * @return
     */
    int delOrder(Integer[] orderIds);

    /**
     * 根据用户id获取该用户的订单
     * @param userId
     * @return
     */
    List<TbOrder> listOrderByUserId(Integer userId);
}
