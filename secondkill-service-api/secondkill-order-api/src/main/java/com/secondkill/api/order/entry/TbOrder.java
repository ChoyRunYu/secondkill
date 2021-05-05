package com.secondkill.api.order.entry;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cairunyu
 * @date 2021/02/11
 * 订单实体类
 */
public class TbOrder {

    private Integer id;
    private Integer goodsId;
    private Integer msGoodsId;
    private Integer userId;
    private String goodsTitle;
    private Integer count;
    private BigDecimal unitPrice;
    private Integer orderState;
    private Date createTime;
    private Date payTime;

    private String username;
    private String goodsImg;

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

    public Integer getMsGoodsId() {
        return msGoodsId;
    }

    public void setMsGoodsId(Integer msGoodsId) {
        this.msGoodsId = msGoodsId;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "TbOrder{" +
                "id=" + id +
                ", goodsId='" + goodsId + '\'' +
                ", msGoodsId=" + msGoodsId +
                ", userId=" + userId +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", count=" + count +
                ", unitPrice=" + unitPrice +
                ", orderState=" + orderState +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                '}';
    }
}
