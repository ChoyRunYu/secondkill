package com.secondkill.system.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.common.utils.RedisUtils;
import com.secondkill.common.utils.TimeUtils;
import com.secondkill.system.goods.mapper.TbGoodsMapper;
import com.secondkill.system.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author choy
 * @date 2021/03/11
 * 商品service实现类
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private TbGoodsMapper goodsMapper;
    @Autowired
    private RedisUtils redisUtils;



    /**
     * 获取秒杀商品列表
     * @return
     */
    @Override
    public List<TbMsGoods> listMsGoods() {
        List<TbMsGoods> listGoods = goodsMapper.listGoods();
        return listGoods;
    }

    /**
     * 根据商品id获取商品详情信息
     * @param id
     * @return
     */
    @Override
    public TbMsGoods getMsGoodsById(Integer id) {
        String tbMsGoodsString = redisUtils.get("msGoods:" + id);
        String stock = redisUtils.get("stock:" + id);
        TbMsGoods tbMsGoods = null;
        if (StringUtils.isEmpty(tbMsGoodsString)){
            tbMsGoods = goodsMapper.getMsGoodsById(id);
            int second = TimeUtils.calSecond(tbMsGoods.getEndTime()) + 3600;
            try{
                redisUtils.setWithTime("msGoods:" + tbMsGoods.getMsGoodsId(), JSON.toJSONString(tbMsGoods), second);
            }catch (Exception e){
                logger.warn(e.getMessage());
            }
        }else{
            tbMsGoods = JSONObject.parseObject(tbMsGoodsString, TbMsGoods.class);
        }
        tbMsGoods.setMsGoodsStock(Integer.parseInt(stock == null ? "0" : stock));
        return tbMsGoods;
    }


    /**
     * 搜索商品
     * @param keyword
     * @return
     */
    @Override
    public List<TbMsGoods> listMsGoodsBySearch(String keyword) {
        return goodsMapper.listGoodsBySearch(keyword);
    }
}
