package com.secondkill.api.order.vo;

/**
 * 前台展示用户订单VO实体类
 * @author choy
 * @date 2021/03/31
 */
public class ListUserOrderVO {

    private Integer id;
    private String goodsTitle;
    private Integer count;
    private String unitPrice;
    private Integer orderState;
    private String createTime;
    private String payTime;
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

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    @Override
    public String toString() {
        return "ListUserOrderVO{" +
                "id=" + id +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", count=" + count +
                ", unitPrice='" + unitPrice + '\'' +
                ", orderState=" + orderState +
                ", createTime='" + createTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                '}';
    }
}
