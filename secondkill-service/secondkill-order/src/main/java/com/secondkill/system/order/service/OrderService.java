package com.secondkill.system.order.service;


import com.secondkill.api.order.entry.TbOrder;
import com.secondkill.api.order.vo.ListOrderVO;
import com.secondkill.api.order.vo.ListUserOrderVO;

import java.util.List;

/**
 * 订单service层实现类
 * @author Choy
 * @date 2021/03/28
 */
public interface OrderService {

    /**
     * 创建订单
     * @param msGoodsId
     * @param userId
     * @return
     */
    int addOrder(Integer msGoodsId, Integer userId);

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    TbOrder getOrderById(Integer orderId);

    /**
     * 支付订单，修改订单状态
     * @param orderId
     * @return
     */
    int payUpdateOrder(Integer orderId);

    /**
     * 分页列出所有订单
     * @param page
     * @param total
     * @return
     */
    List<TbOrder> listOrder(Integer page, Integer total);

    /**
     * 统计订单总数
     * @return
     */
    int countOrder();

    /**
     * 删除订单
     * @param ids
     * @return
     */
    int delOrder(Integer[] ids);

    /**
     * 根据用户id获取该用户的订单列表
     * @param userId
     * @return
     */
    List<ListUserOrderVO> listOrderByUserId(Integer userId);

}
