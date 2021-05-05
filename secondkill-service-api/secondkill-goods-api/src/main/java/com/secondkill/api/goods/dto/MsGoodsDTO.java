package com.secondkill.api.goods.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Choy
 * @date 2021/03/10
 * 秒杀商品实体类dto
 */

public class MsGoodsDTO implements Serializable {

    @NotNull(message = "商品id不能为空")
    private Integer goodsId;
    @NotNull(message = "商品秒杀价不能为空")
    private BigDecimal msPrice;
    @NotNull(message = "商品秒杀库存不能为空")
    private Integer msGoodsStock;
    @NotNull(message = "秒杀开始时间不能为空")
    private String startTime;
    @NotNull(message = "秒杀技结束时间不能为空")
    private String endTime;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public BigDecimal getMsPrice() {
        return msPrice;
    }

    public void setMsPrice(BigDecimal msPrice) {
        this.msPrice = msPrice;
    }

    @Override
    public String toString() {
        return "MsGoodsDTO{" +
                "goodsId=" + goodsId +
                ", msPrice=" + msPrice +
                ", msGoodsStock=" + msGoodsStock +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }


}
