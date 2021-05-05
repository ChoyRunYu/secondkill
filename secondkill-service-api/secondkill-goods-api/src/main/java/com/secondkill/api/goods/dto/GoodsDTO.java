package com.secondkill.api.goods.dto;

import com.secondkill.api.goods.entry.TbMsGoods;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author choy
 * @date 2021/03/12
 * 商品dto
 */
public class GoodsDTO {

    @NotNull(message = "商品名称不能为空")
    @NotBlank(message = "商品名称不能为空")
    private String goodsName;
    @NotNull(message = "商品标题不能为空")
    @NotBlank(message = "商品标题不能为空")
    private String goodsTitle;
    @NotNull(message = "商品图片不能为空")
    @NotBlank(message = "商品图片不能为空")
    private String goodsImg;
    @NotNull(message = "商品价格不能为空")
    private BigDecimal goodsPrice;
    @NotNull(message = "商品库存不能为空")
    private Integer goodsStock;


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

    @Override
    public String toString() {
        return "GoodsDTO{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                '}';
    }
}
