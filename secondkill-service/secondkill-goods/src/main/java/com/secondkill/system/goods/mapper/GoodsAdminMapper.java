package com.secondkill.system.goods.mapper;

import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author choy
 * @date 2021/03/10
 * 商品管理mapper
 */
public interface GoodsAdminMapper {

    /**
     * 根据id获取一条商品链接
     * @param id
     * @return
     */
    TbGoods getGoodsById(Integer id);

    /**
     * 添加秒杀商品
     * @return
     */
    int addMsGoods(TbMsGoods tbMsGoods);

    /**
     * 添加商品
     * @return
     */
    int addGoods(TbGoods tbGoods);

    /**
     * 获取一条秒杀商品信息
     * @param id
     * @return
     */
    TbMsGoods getMsGoodsById(Integer id);

    /**
     * 根据分页获取商品列表
     * @param page
     * @param total
     * @return
     */
    List<TbGoods> listGoods(@Param("page")Integer page, @Param("total")Integer total);

    /**
     * 统计商品总数
     * @return
     */
    int countGoods();

    /**
     * 根据id删除一条商品记录
     * @param goodsIds
     * @return
     */
    int delGoods(Integer[] goodsIds);

    /**
     * 编辑一条商品信息
     * @param tbGoods
     * @return
     */
    int updateGoods(TbGoods tbGoods);

    /**
     * 根据分页获取秒杀列表
     * @param page
     * @param total
     * @return
     */
    List<TbMsGoods> listMsGoods(@Param("page")Integer page, @Param("total")Integer total);

    /**
     * 统计秒杀列表总数
     * @return
     */
    int countMsGoods();

    /**
     * 删除秒杀商品信息
     * @param msGoodsIds
     * @return
     */
    int delMsGoods(Integer[] msGoodsIds);

    /**
     * 根据id获取秒杀活动
     * @param id
     * @return
     */
    TbMsGoods getMsActivityById(Integer id);

    /**
     * 修改秒杀活动信息
     * @param tbMsGoods
     * @return
     */
    int updateMsActivity(TbMsGoods tbMsGoods);

    /**
     * 根据商品ids删除秒杀活动
     * @param goodsIds
     * @return
     */
    int delMsGoodsByGoodsId(Integer[] goodsIds);

}
