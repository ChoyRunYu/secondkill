package com.secondkill.system.order.controller;


import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.api.goods.feign.GoodsFeign;
import com.secondkill.api.goods.vo.MsGoodsDetailVO;
import com.secondkill.api.order.entry.TbOrder;
import com.secondkill.api.order.vo.ListOrderVO;
import com.secondkill.api.order.vo.ListUserOrderVO;
import com.secondkill.api.order.vo.PayOrderVo;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.*;
import com.secondkill.system.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author choy
 * @date 2021/02/14
 * 订单controller层
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisLuaUtils redisLuaUtils;
    @Autowired
    private GoodsFeign goodsFeign;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private OrderService orderService;

    /**
     * 点击立即抢购按钮后检验库存，并跳转确认订单页面
     * @param msGoodsId
     * @return
     */
    @GetMapping("/checkStock/{msGoodsId}")
    public Result checkStock(@PathVariable("msGoodsId") Integer msGoodsId){
        String stock = redisUtils.get("stock:" + msGoodsId);
        if (stock == null){
            return ResultUtils.success("活动结束", "none");
        }
        if (Integer.parseInt(stock) > 0 ){
            return ResultUtils.success("请求成功", "true");
        }
        return ResultUtils.success("您够买的商品库存不足", "end");
    }

    /**
     * 确认订单页面生成订单信息接口
     * @return
     */
    @GetMapping("/buy/{msGoodsId}")
    public Result generateOrderMess(@PathVariable("msGoodsId")Integer msGoodsId){
        if (msGoodsId == null || msGoodsId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        Optional<Result<MsGoodsDetailVO>> msGoods = Optional.ofNullable(goodsFeign.getMsGoods(msGoodsId));
        if (!msGoods.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        return ResultUtils.success(msGoods.get().getData());
    }


    /**
     * 生成订单，先预扣库存
     * @param msGoodsId
     * @return
     */
    @PostMapping("/createOrder/{msGoodsId}")
    public Result generateOrder(@PathVariable("msGoodsId")Integer msGoodsId, HttpServletRequest request){
        if(msGoodsId == null || msGoodsId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        //获取用户id
        String token = request.getHeader("Authorization");
        Integer userId = jwtUtils.getTokenUserId(token);
        // 扣减库存
        String res = redisLuaUtils.cutStock(msGoodsId, userId);
        // 已经参加过活动
        if ("isBuy".equals(res)){
            return ResultUtils.error(40004, "您已经参加过活动！");
        }else if("end".equals(res)){
            // 库存不足
            return ResultUtils.error(40004, "您购买的商品库存不足！");
        }else if("true".equals(res)){
            // 库存足够且扣减库存,然后创建订单
            int affectRows = orderService.addOrder(msGoodsId, userId);
            if (affectRows <= 0) {
                throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
            }
            return ResultUtils.success("下单成功", affectRows);
        }else{
            return ResultUtils.success("活动结束");
        }
    }


    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    @GetMapping("/getById/{orderId}")
    public Result getOrderById(@PathVariable("orderId")Integer orderId){
        if (orderId == null || orderId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        Optional<TbOrder> orderById = Optional.ofNullable(orderService.getOrderById(orderId));
        if (!orderById.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        PayOrderVo payOrderVo = new PayOrderVo();
        BeanUtils.copyProperties(orderById.get(), payOrderVo);
        payOrderVo.setUnitPrice(orderById.get().getUnitPrice().toString());
        return ResultUtils.success(payOrderVo);
    }


    /**
     * 支付订单
     * @param orderId
     * @return
     */
    @PostMapping("/payOrder/{orderId}")
    public Result payOrder(@PathVariable("orderId") Integer orderId){
        if(orderId == null || orderId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        int affectRows = orderService.payUpdateOrder(orderId);
        if (affectRows == 0){
            return ResultUtils.error(40004, "订单已支付");
        }else if(affectRows == 2){
            return ResultUtils.error(40004, "订单已过期");
        }else{
            return ResultUtils.success("支付成功", true);
        }
    }



    /**
     * 根据用户id获取用户的订单列表
     * @param request
     * @return
     */
    @GetMapping("/listOrder")
    public Result listOrderByUserId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Integer userId = jwtUtils.getTokenUserId(token);
        try{
            List<ListUserOrderVO> listOrderVOList = orderService.listOrderByUserId(userId);
            return ResultUtils.success(listOrderVOList);
        }catch (AppRuntimeException e){
            return ResultUtils.success("该用户暂时没有订单");
        }
    }

}
