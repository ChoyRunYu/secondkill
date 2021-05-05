package com.secondkill.api.order.vo;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀订单列表VO实体类
 * @author choy
 * @date 2021/03/31
 */
public class ListOrderVO {

    private Integer id;
    private String goodsTitle;
    private Integer count;
    private String unitPrice;
    private Integer orderState;
    private String createTime;
    private String payTime;
    private String username;
    private String goodsImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    @Override
    public String toString() {
        return "ListOrderVO{" +
                "id=" + id +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", count=" + count +
                ", unitPrice=" + unitPrice +
                ", orderState=" + orderState +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", username='" + username + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                '}';
    }
}
