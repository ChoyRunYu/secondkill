package com.secondkill.api.goods.entry;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cairunyu
 * @date 2021/02/10
 * 商品实体类
 */
public class TbGoods {

    private static final long serialVersionUID = -919201640201914789L;

    private Integer goodsId;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private BigDecimal goodsPrice;
    private Integer goodsStock;
    private String goodsDetail;
    private Date createTime;
    private TbMsGoods tbMsGoods;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TbMsGoods getTbMsGoods() {
        return tbMsGoods;
    }

    public void setTbMsGoods(TbMsGoods tbMsGoods) {
        this.tbMsGoods = tbMsGoods;
    }

    @Override
    public String toString() {
        return "TbGoods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", createTime=" + createTime +
                ", tbMsGoods=" + tbMsGoods +
                '}';
    }
}
