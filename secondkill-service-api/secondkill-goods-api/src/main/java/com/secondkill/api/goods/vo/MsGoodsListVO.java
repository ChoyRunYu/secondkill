package com.secondkill.api.goods.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author choy
 * @date 2021/03/11
 * 秒杀商品信息vo
 */
public class MsGoodsListVO {

    private Integer goodsId;
    private Integer msGoodsId;
    private String goodsTitle;
    private String goodsImg;
    private String goodsPrice;
    private String msPrice;
    private Integer msGoodsStock;
    private String startTime;
    private String endTime;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
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
}
