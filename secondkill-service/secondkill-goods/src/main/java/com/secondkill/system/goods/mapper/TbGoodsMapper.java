package com.secondkill.system.goods.mapper;

import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;

import java.util.List;

/**
 * @author choy
 * @date 2021/03/11
 * 商品mapper类
 */
public interface TbGoodsMapper {

    /**
     * 获取所有秒杀商品列表
     * @return
     */
    List<TbMsGoods> listGoods();

    /**
     * 根据id获取秒杀商品详情
     * @param id
     * @return
     */
    TbMsGoods getMsGoodsById(Integer id);

    /**
     * 更新秒杀活动库存
     * @param msGoodsId
     * @return
     */
    int updateMsGoodsStock(Integer msGoodsId);

    /**
     * 更新秒杀活动库存
     * @param goodsId
     * @return
     */
    int updateGoodsStock(Integer goodsId);


    /**
     * 搜索秒杀商品
     * @return
     */
    List<TbMsGoods> listGoodsBySearch(String keyword);
}
