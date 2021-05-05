package com.secondkill.api.goods.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author choy
 * @date 2021/03/17
 * 秒杀活动基本DTO实体类
 */
public class MsGoodsBaseDTO {

    @NotNull(message = "秒杀活动id不能为空")
    @Min(value = 0, message = "秒杀活动id不能为0")
    private Integer msGoodsId;
    @NotNull(message = "价格不能为空")
    private BigDecimal msPrice;
    @NotNull(message = "秒杀库存不能为空")
    @Min(value = 0, message = "秒杀库存不能为0")
    private Integer msGoodsStock;
    @NotNull(message = "秒杀开始时间不能为空")
    @NotEmpty(message = "秒杀开始时间不能为空")
    private String startTime;
    @NotNull(message = "秒杀结束时间不能为空")
    @NotEmpty(message = "秒杀结束时间不能为空")
    private String endTime;

    public Integer getMsGoodsId() {
        return msGoodsId;
    }

    public void setMsGoodsId(Integer msGoodsId) {
        this.msGoodsId = msGoodsId;
    }

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

    @Override
    public String toString() {
        return "MsGoodsBaseVO{" +
                "msGoodsId=" + msGoodsId +
                ", msPrice='" + msPrice + '\'' +
                ", msGoodsStock=" + msGoodsStock +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
