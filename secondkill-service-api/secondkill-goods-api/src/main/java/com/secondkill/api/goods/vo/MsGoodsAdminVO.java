package com.secondkill.api.goods.vo;


/**
 * @author choy
 * @date 2021/03/15
 * 秒杀商品管理vo实体类
 */
public class MsGoodsAdminVO {

    private Integer msGoodsId;
    private String goodsTitle;
    private String goodsImg;
    private String msPrice;
    private Integer msGoodsStock;
    private String createTime;
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

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MsGoodsAdminVO{" +
                ", msGoodsId=" + msGoodsId +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", msPrice='" + msPrice + '\'' +
                ", msGoodsStock=" + msGoodsStock +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
