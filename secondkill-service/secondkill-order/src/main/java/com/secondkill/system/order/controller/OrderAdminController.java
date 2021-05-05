package com.secondkill.system.order.controller;

import com.secondkill.api.order.entry.TbOrder;
import com.secondkill.api.order.vo.ListOrderVO;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.JwtUtils;
import com.secondkill.common.utils.Result;
import com.secondkill.common.utils.ResultUtils;
import com.secondkill.common.utils.TimeUtils;
import com.secondkill.system.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * 订单管理接口
 * @author choy
 * @date 2021/03/31
 */
@RestController
@RequestMapping("/order/admin")
public class OrderAdminController {

    private final static Logger logger = LoggerFactory.getLogger(OrderAdminController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 分页列出所有订单列表
     * @param page 分页码数
     * @return
     */
    @GetMapping("/listOrder/{page}")
    public Result listOrderByPage(@PathVariable("page")Integer page){
        // 分页参数校验
        if (page == null || page == 0){
            page = 1;
        }
        // 获取用户总条数，分页模块用
        int countOrder = orderService.countOrder();
        if (countOrder == 0) return ResultUtils.success("订单为空");
        int dbPage = countOrder % 10 == 0 ? countOrder / 10 : countOrder / 10 + 1;
        // 如果传入超过数据库总页数，则默认返回最后一页
        if (dbPage < page){
            page = dbPage;
        }
        Optional<List<TbOrder>> optionalTbOrderList = Optional.ofNullable(orderService.listOrder(10 * (page - 1), 10));
        if (!optionalTbOrderList.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        List<ListOrderVO> listOrderVOList = new ArrayList<>(10);
        for (TbOrder tbOrder : optionalTbOrderList.get()) {
            ListOrderVO listOrderVO = new ListOrderVO();
            BeanUtils.copyProperties(tbOrder, listOrderVO);
            if (tbOrder.getPayTime() != null){
                listOrderVO.setPayTime(TimeUtils.date2String(tbOrder.getPayTime()));
            }
            listOrderVO.setCreateTime(TimeUtils.date2String(tbOrder.getCreateTime()));
            listOrderVO.setUnitPrice(tbOrder.getUnitPrice().toString());
            listOrderVOList.add(listOrderVO);
        }
        HashMap resMap = new HashMap(2);
        resMap.put("total", countOrder);
        resMap.put("orderList", listOrderVOList);
        return ResultUtils.success(resMap);
    }

    /**
     * 删除订单
     * @param reqMap
     * @return
     */
    @PostMapping("/delOrder")
    public Result delOrderById(@RequestBody HashMap<String, Integer[]> reqMap){
        Integer[] orderIds = reqMap.get("orderIds");
        if (orderIds == null || orderIds.length == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        int affectRows = orderService.delOrder(orderIds);
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.DEL_ACTION_FAIL);
        }
        return ResultUtils.success("删除成功");
    }

}
