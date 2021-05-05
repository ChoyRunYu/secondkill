package com.secondkill.system.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.secondkill.api.goods.dto.EditGoodsDTO;
import com.secondkill.api.goods.dto.GoodsDTO;
import com.secondkill.api.goods.dto.MsGoodsBaseDTO;
import com.secondkill.api.goods.dto.MsGoodsDTO;
import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.api.goods.redis.MsGoodsDO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * @author choy
 * @date 2021/03/10
 * 商品service类
 */
public interface GoodsAdminService {

    /**
     * 根据id获取商品详情
     * @param id
     * @return
     */
    TbGoods getGoodsById(Integer id);

    /**
     * 根据分页获取商品列表
     * @return
     */
    List<TbGoods> listGoods(Integer page, Integer total);


    /**
     * 发布新商品
     * @param goodsDTO
     * @return
     */
    int addGoods(GoodsDTO goodsDTO);

    /**
     * 添加秒杀商品
     * @return
     */
    int addMsGoods(MsGoodsDTO msGoodsDTO) throws ParseException;

    /**
     * jiang
     * @param id
     * @return
     */
    int msGoodsAddRedis(Integer id);

    /**
     * 统计商品总条数
     * @return
     */
    int countGoods();

    /**
     * 删除商品信息
     * @param goodsId
     * @return
     */
    int delGoods(Integer[] goodsId);

    /**
     * 根据分页获取秒杀列表
     * @param page
     * @param total
     * @return
     */
    List<TbMsGoods> listMsGoods(Integer page, Integer total);

    /**
     * 统计秒杀总数
     * @return
     */
    int countMsGoods();

    /**
     * 根据id删除秒杀商品信息
     * @param msGoodsIds
     * @return
     */
    int delMsGoods(Integer[] msGoodsIds);

    /**
     * 根据id获取一条秒杀活动
     * @param id
     * @return
     */
    TbMsGoods getMsActivityById(Integer id);

    /**
     * 修改秒杀活动信息
     * @param msGoodsBaseDTO
     * @return
     */
    int updateMsActivity(MsGoodsBaseDTO msGoodsBaseDTO) throws ParseException;

    /**
     * 商品图片上传
     * @param file
     * @return
     */
    String imgUpload(MultipartFile file);


    /**
     * 编辑商品信息
     * @param editGoodsDTO
     * @return
     */
    int updateGoods(EditGoodsDTO editGoodsDTO);
}
