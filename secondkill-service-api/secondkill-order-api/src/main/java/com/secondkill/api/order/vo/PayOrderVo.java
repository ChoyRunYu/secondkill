package com.secondkill.api.order.vo;

/**
 * 支付页面订单信息vo实体类
 * @author Choy
 * @date 2021/03/26
 */
public class PayOrderVo {

    private Integer id;
    private String goodsTitle;
    private String unitPrice;
    private Integer count;
    private Integer orderState;

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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "PayOrderVo{" +
                "id=" + id +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", count=" + count +
                ", orderState=" + orderState +
                '}';
    }
}
