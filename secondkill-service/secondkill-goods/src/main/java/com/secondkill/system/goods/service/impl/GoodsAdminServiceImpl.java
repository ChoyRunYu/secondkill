package com.secondkill.system.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.secondkill.api.goods.dto.EditGoodsDTO;
import com.secondkill.api.goods.dto.GoodsDTO;
import com.secondkill.api.goods.dto.MsGoodsBaseDTO;
import com.secondkill.api.goods.dto.MsGoodsDTO;
import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.common.utils.RedisUtils;
import com.secondkill.common.utils.TimeUtils;
import com.secondkill.system.goods.mapper.GoodsAdminMapper;
import com.secondkill.system.goods.service.GoodsAdminService;
import com.secondkill.common.utils.OssUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author cairunyu
 * @date 2021/02/04
 * 商品service层
 */
@Service
@Transactional
public class GoodsAdminServiceImpl implements GoodsAdminService  {

    private static final Logger logger = LoggerFactory.getLogger(GoodsAdminServiceImpl.class);
    @Autowired
    private GoodsAdminMapper goodsAdminMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private OssUtils ossUtils;

    /**
     * 根据商品id获取商品详情
     * @param id
     * @return
     */
    @Override
    public TbGoods getGoodsById(Integer id) {
        return goodsAdminMapper.getGoodsById(id);
    }

    /**
     * 获取商品列表
     * @return
     */
    @Override
    public List<TbGoods> listGoods(Integer page, Integer total){
        return goodsAdminMapper.listGoods(page, total);
    }

    /**
     * 发布新商品
     * @param goodsDTO
     * @return
     */
    @Override
    public int addGoods(GoodsDTO goodsDTO) {
        TbGoods tbGoods = new TbGoods();
        BeanUtils.copyProperties(goodsDTO, tbGoods);
        tbGoods.setCreateTime(new Date());
        return goodsAdminMapper.addGoods(tbGoods);
    }

    /**
     * 添加秒杀活动
     * @param msGoodsDTO
     * @return
     */
    @Override
    public int addMsGoods(MsGoodsDTO msGoodsDTO) throws ParseException {
        // bean转换
        TbMsGoods tbMsGoods = new TbMsGoods();
        BeanUtils.copyProperties(msGoodsDTO, tbMsGoods);
        tbMsGoods.setMsPrice(new BigDecimal(String.valueOf(msGoodsDTO.getMsPrice())).setScale(2));
        // 查询商品是否存在
        Optional<TbGoods> goodsById = Optional.ofNullable(goodsAdminMapper.getGoodsById(tbMsGoods.getGoodsId()));
        // 如果商品不存在
        if (!goodsById.isPresent()){
            return 0;
        }
        tbMsGoods.setCreateTime(new Date());
        tbMsGoods.setStartTime(TimeUtils.string2Date(msGoodsDTO.getStartTime()));
        tbMsGoods.setEndTime(TimeUtils.string2Date(msGoodsDTO.getEndTime()));
        tbMsGoods.setIsUp(1);
        // 添加秒杀活动
        int affectRows = goodsAdminMapper.addMsGoods(tbMsGoods);
        if (affectRows <= 0) {
            return 0;
        }
        tbMsGoods.setTbGoods(goodsById.get());
        int second = TimeUtils.calSecond(tbMsGoods.getEndTime());
        // 缓存,缓存到期时间为商品秒杀结束时间的后1小时
        redisUtils.setWithTime("msGoods:" + tbMsGoods.getMsGoodsId(), JSON.toJSON(tbMsGoods).toString(), second + 3600);
        redisUtils.setWithTime("stock:" + tbMsGoods.getMsGoodsId(), tbMsGoods.getMsGoodsStock().toString(), second + 3600);
        return affectRows;
    }

    /**
     * 预热秒杀商品信息
     * @param id
     * @return
     */
    @Override
    public int msGoodsAddRedis(Integer id) {
        Optional<TbMsGoods> msGoodsById = Optional.ofNullable(goodsAdminMapper.getMsGoodsById(id));
        if (!msGoodsById.isPresent()){
            return 0;
        }
        TbMsGoods tbMsGoods = msGoodsById.get();
        int second = TimeUtils.calSecond(tbMsGoods.getEndTime()) + 3600;
        redisUtils.setWithTime("msGoods:" + tbMsGoods.getMsGoodsId(), JSON.toJSONString(tbMsGoods), second);
        return 1;
    }

    /**
     * 统计商品总条数
     * @return
     */
    @Override
    public int countGoods() {
        return goodsAdminMapper.countGoods();
    }

    /**
     * 删除商品信息
     * @param goodsIds
     * @return
     */
    @Override
    public int delGoods(Integer[] goodsIds) {
        int affectRows = goodsAdminMapper.delGoods(goodsIds);
        if (affectRows > 0){
            goodsAdminMapper.delMsGoodsByGoodsId(goodsIds);
        }
        return affectRows;
    }

    /**
     * 根据分页获取秒杀列表
     * @param page
     * @param total
     * @return
     */
    @Override
    public List<TbMsGoods> listMsGoods(Integer page, Integer total) {
        return goodsAdminMapper.listMsGoods(page, total);
    }

    /**
     * 统计秒杀总数
     * @return
     */
    @Override
    public int countMsGoods() {
        return goodsAdminMapper.countMsGoods();
    }

    /**
     * 根据id删除秒杀商品信息
     * @param msGoodsIds
     * @return
     */
    @Override
    public int delMsGoods(Integer[] msGoodsIds) {
        return goodsAdminMapper.delMsGoods(msGoodsIds);
    }

    /**
     * 根据id获取一条秒杀活动
     * @param id
     * @return
     */
    @Override
    public TbMsGoods getMsActivityById(Integer id) {
        return goodsAdminMapper.getMsActivityById(id);
    }



    /**
     * 修改秒杀活动信息
     * @param msGoodsBaseDTO
     * @return
     */
    @Override
    public int updateMsActivity(MsGoodsBaseDTO msGoodsBaseDTO) throws ParseException {
        TbMsGoods tbMsGoods = new TbMsGoods();
        BeanUtils.copyProperties(msGoodsBaseDTO, tbMsGoods);
        tbMsGoods.setStartTime(TimeUtils.string2Date(msGoodsBaseDTO.getStartTime()));
        tbMsGoods.setEndTime(TimeUtils.string2Date(msGoodsBaseDTO.getEndTime()));
        return goodsAdminMapper.updateMsActivity(tbMsGoods);
    }

    /**
     * 商品图片上传
     * @param multipartFile
     * @return
     */
    @Override
    public String imgUpload(MultipartFile multipartFile) {
        File file = null;
        String filename = multipartFile.getOriginalFilename();
        try{
            file = new File(getFilePath(filename));
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        }catch (IOException e){
            logger.error("找不到文件");
            return "false";
        }
        String res = ossUtils.uploadFile2Oss(file);
        if (!StringUtils.isEmpty(res)){
            if (file.exists()){
                file.delete();
            }
        }
        return res;
    }

    /**
     * 编辑商品信息
     * @param editGoodsDTO
     * @return
     */
    @Override
    public int updateGoods(EditGoodsDTO editGoodsDTO) {
        TbGoods tbGoods = new TbGoods();
        BeanUtils.copyProperties(editGoodsDTO, tbGoods);
        return goodsAdminMapper.updateGoods(tbGoods);
    }


    /**
     * 上传的目录
     * 目录: 根据年月日归档
     * 文件名: 时间戳 + 随机数
     * @param fileName
     * @return
     */
    private String getFilePath(String fileName) {
        Long time = new Date().getTime();
        return time.toString() + RandomUtils.nextInt(100, 999999)
                + "."
                + StringUtils.substringAfterLast(fileName, ".");
    }


}
