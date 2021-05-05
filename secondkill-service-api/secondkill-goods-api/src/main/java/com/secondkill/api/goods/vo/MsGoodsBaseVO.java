package com.secondkill.api.goods.vo;

/**
 * @author choy
 * @date 2021/03/17
 * 秒杀活动基本vo实体类
 */
public class MsGoodsBaseVO {

    private Integer msGoodsId;
    private String msPrice;
    private Integer msGoodsStock;
    private String startTime;
    private String endTime;
    private Integer goodsStock;

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Integer getMsGoodsId() {
        return msGoodsId;
    }

    public void setMsGoodsId(Integer msGoodsId) {
        this.msGoodsId = msGoodsId;
    }

    public String getMsPrice() {
        return msPrice;
    }

    public void setMsPrice(String msPrice) {
        this.msPrice = msPrice;
    }

    public Integer getMsGoodsStock() {
        return msGoodsStock;
    }

    public void setMsGoodsStock(Integer msGoodsStock) {
        this.msGoodsStock = msGoodsStock;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "MsGoodsBaseVO{" +
                "msGoodsId=" + msGoodsId +
                ", msPrice='" + msPrice + '\'' +
                ", msGoodsStock=" + msGoodsStock +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", goodsStock=" + goodsStock +
                '}';
    }
}
