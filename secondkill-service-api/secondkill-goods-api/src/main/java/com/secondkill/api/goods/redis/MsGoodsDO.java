package com.secondkill.api.goods.redis;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author choy
 * @date 2021/03/13
 * 秒杀商品缓存实体类
 */
public class MsGoodsDO implements Serializable {

    private BigDecimal msPrice;
    private Integer msGoodsStock;
    private String startTime;
    private String endTime;
    private String goodsName;
    private String goodsTitle;
    private String GoodsImg;
    private BigDecimal goodsPrice;
    private Integer goodsStock;
    private String goodsDetail;

    public BigDecimal getMsPrice() {
        return msPrice;
    }

    public void setMsPrice(BigDecimal msPrice) {
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return GoodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        GoodsImg = goodsImg;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    @Override
    public String toString() {
        return "MsGoodsDO{" +
                "msPrice=" + msPrice +
                ", msGoodsStock=" + msGoodsStock +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", GoodsImg='" + GoodsImg + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                ", goodsDetail='" + goodsDetail + '\'' +
                '}';
    }
}
