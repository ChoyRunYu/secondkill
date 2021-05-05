package com.secondkill.api.goods.entry;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cairunyu
 * @date 2021/02/11
 * 秒杀商品实体类
 */
public class TbMsGoods implements Serializable {

    private static final long serialVersionUID = -919201640201914789L;

    private Integer msGoodsId;
    private Integer goodsId;
    private BigDecimal msPrice;
    private Integer msGoodsStock;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Integer isUp;
    private TbGoods tbGoods;

    public Integer getMsGoodsId() {
        return msGoodsId;
    }

    public void setMsGoodsId(Integer msGoodsId) {
        this.msGoodsId = msGoodsId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsUp() {
        return isUp;
    }

    public void setIsUp(Integer isUp) {
        this.isUp = isUp;
    }

    public TbGoods getTbGoods() {
        return tbGoods;
    }



    @Override
    public String toString() {
        return "TbMsGoods{" +
                "msGoodsId=" + msGoodsId +
                ", goodsId=" + goodsId +
                ", msPrice=" + msPrice +
                ", msGoodsStock=" + msGoodsStock +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", isUp=" + isUp +
                ", tbGoods=" + tbGoods +
                '}';
    }

    public void setTbGoods(TbGoods tbGoods) {
        this.tbGoods = tbGoods;
    }

}
