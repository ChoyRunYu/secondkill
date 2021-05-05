package com.secondkill.system.goods.service;

import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;

import java.util.List;

/**
 * @author choy
 * @date 2021/03/11
 * 商品实体类
 */
public interface GoodsService {

    /**
     * 获取秒杀商品列表
     * @return
     */
    List<TbMsGoods> listMsGoods();

    /**
     * 根据商品id获取商品详情信息
     * @param id
     * @return
     */
    TbMsGoods getMsGoodsById(Integer id);

    /**
     * 搜索商品
     * @return
     */
    List<TbMsGoods> listMsGoodsBySearch(String keyword);
}
