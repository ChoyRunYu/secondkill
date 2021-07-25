package com.secondkill.system.goods.controller;

import com.secondkill.api.goods.dto.EditGoodsDTO;
import com.secondkill.api.goods.dto.GoodsDTO;
import com.secondkill.api.goods.dto.MsGoodsBaseDTO;
import com.secondkill.api.goods.dto.MsGoodsDTO;
import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.api.goods.vo.GoodsAdminVO;
import com.secondkill.api.goods.vo.MsGoodsAdminVO;
import com.secondkill.api.goods.vo.MsGoodsBaseVO;
import com.secondkill.api.user.feign.IUserFeign;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.*;
import com.secondkill.system.goods.service.GoodsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author cairunyu
 * @date 2021/02/01
 */
@RestController
@RequestMapping("/goods")
public class GoodsAdminController {

    private final static Logger logger = LoggerFactory.getLogger(GoodsAdminController.class);

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private GoodsAdminService goodsAdminService;
    @Autowired
    private IUserFeign userFegin;




    /**
     * 根据id获取一条商品信息
     * @param goodsId
     * @return
     */
    @GetMapping("/admin/getGoods/{goodsId}")
    public Result getGoodsById(@PathVariable("goodsId")Integer goodsId){
        if (goodsId == null || goodsId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        Optional<TbGoods> goodsById = Optional.ofNullable(goodsAdminService.getGoodsById(goodsId));
        if (!goodsById.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        GoodsAdminVO goodsAdminVO = new GoodsAdminVO();
        BeanUtils.copyProperties(goodsById.get(), goodsAdminVO);
        goodsAdminVO.setCreateTime(TimeUtils.date2String(goodsById.get().getCreateTime()));
        return ResultUtils.success(goodsAdminVO);
    }

    /**
     * 获取商品管理列表
     * @return
     */
    @GetMapping("/admin/listGoods/{page}")
    public Result listGoods(@PathVariable("page")Integer page){
        // 分页参数校验
        if (page == null || page == 0){
            page = 1;
        }
        // 获取用户总条数，分页模块用
        int countGoods = goodsAdminService.countGoods();
        int dbPage = countGoods % 10 == 0 ? countGoods / 10 : countGoods / 10 + 1;
        // 如果传入超过数据库总页数，则默认返回最后一页
        if (dbPage < page){
            page = dbPage;
        }
        Optional<List<TbGoods>> optionalTbGoodsList = Optional.ofNullable(goodsAdminService.listGoods(10 * (page - 1), 10));
        if (!optionalTbGoodsList.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        List<GoodsAdminVO> goodsAdminVOList = new ArrayList<>();
        for (TbGoods tbGoods : optionalTbGoodsList.get()) {
            GoodsAdminVO goodsAdminVO = new GoodsAdminVO();
            BeanUtils.copyProperties(tbGoods, goodsAdminVO);
            goodsAdminVO.setGoodsPrice(tbGoods.getGoodsPrice().toString());
            goodsAdminVO.setCreateTime(TimeUtils.date2String(tbGoods.getCreateTime()));
            goodsAdminVOList.add(goodsAdminVO);
        }
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("goodsList", goodsAdminVOList);
        resMap.put("total", countGoods);
        return ResultUtils.success(resMap);
    }

    /**
     * 发布新商品
     * @param goodsDTO
     * @return
     */
    @PostMapping("/admin/addGoods")
    public Result addGoods(@RequestBody @Valid GoodsDTO goodsDTO){
        int affectRows = goodsAdminService.addGoods(goodsDTO);
        if (affectRows <= 0 ){
            throw new AppRuntimeException(ResultErrEnum.ADD_ACTION_FAIL);
        }
        return ResultUtils.success("添加成功");
    }


    /**
     * 添加秒杀商品
     * @param msGoodsDTO
     * @return
     */
    @PostMapping("/admin/addMsGoods")
    public Result addMsGoods(@RequestBody @Valid MsGoodsDTO msGoodsDTO) {
        int affectRows = 0;
        try{
            affectRows = goodsAdminService.addMsGoods(msGoodsDTO);
        }catch (ParseException e){
            throw new AppRuntimeException(ResultErrEnum.ADD_ACTION_FAIL);
        }
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.ADD_ACTION_FAIL);
        }
        return ResultUtils.success("创建成功");
    }

    /// 暂时不用
    /**
     * 将秒杀商品进行库存预热，缓存到redis中
     * @param msGoodsId
     * @return
     */
    /**
    @PostMapping("/admin/msGoodsCache/{msGoodsId}")
    public Result goodsAddRedis(@PathVariable("msGoodsId")Integer msGoodsId){
        if (msGoodsId == null || msGoodsId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        goodsAdminService.msGoodsAddRedis(msGoodsId);
        return ResultUtils.success("缓存成功");
    }*/

    /**
     * 删除商品信息接口
     * @param reqMap
     * @return
     */
    @PostMapping("/admin/delGoods")
    public Result delGoods(@RequestBody HashMap<String, Integer[]> reqMap){
        Integer[] goodsIds = reqMap.get("goodsIds");
        if (goodsIds == null || goodsIds.length <= 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        int affectRows = goodsAdminService.delGoods(goodsIds);
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.DEL_ACTION_FAIL);
        }
        return ResultUtils.success("删除成功");
    }


    /**
     * 获取秒杀管理列表
     * @return
     */
    @GetMapping("/admin/listMsGoods/{page}")
    public Result listMsGoods(@PathVariable("page")Integer page){
        // 分页参数校验
        if (page == null || page == 0){
            page = 1;
        }
        // 获取用户总条数，分页模块用
        int countMsGoods = goodsAdminService.countMsGoods();
        int dbPage = countMsGoods % 10 == 0 ? countMsGoods / 10 : countMsGoods / 10 + 1;
        // 如果传入超过数据库总页数，则默认返回最后一页
        if (dbPage < page){
            page = dbPage;
        }
        Optional<List<TbMsGoods>> optionalTbMsGoodsList = Optional.ofNullable(goodsAdminService.listMsGoods(10 * (page - 1), 10));
        if (!optionalTbMsGoodsList.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        List<MsGoodsAdminVO> goodsAdminVOList = new ArrayList<>();
        for (TbMsGoods tbMsGoods : optionalTbMsGoodsList.get()) {
            MsGoodsAdminVO msGoodsAdminVO = new MsGoodsAdminVO();
            BeanUtils.copyProperties(tbMsGoods, msGoodsAdminVO);
            msGoodsAdminVO.setGoodsImg(tbMsGoods.getTbGoods().getGoodsImg());
            msGoodsAdminVO.setGoodsTitle(tbMsGoods.getTbGoods().getGoodsTitle());
            msGoodsAdminVO.setMsPrice(tbMsGoods.getMsPrice().toString());
            msGoodsAdminVO.setCreateTime(TimeUtils.date2String(tbMsGoods.getCreateTime()));
            msGoodsAdminVO.setStartTime(TimeUtils.date2String(tbMsGoods.getStartTime()));
            msGoodsAdminVO.setEndTime(TimeUtils.date2String(tbMsGoods.getEndTime()));
            msGoodsAdminVO.setGoodsStock(tbMsGoods.getTbGoods().getGoodsStock());
            goodsAdminVOList.add(msGoodsAdminVO);
        }
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msGoodsList", goodsAdminVOList);
        resMap.put("total", countMsGoods);
        return ResultUtils.success(resMap);
    }

    /**
     * 删除秒杀列表
     * @param reqMap
     * @return
     */
    @PostMapping("/admin/delMsGoods")
    public Result delMsGoods(@RequestBody HashMap<String, Integer[]> reqMap){
        Integer[] msGoodsIds = reqMap.get("msGoodsIds");
        if (msGoodsIds == null || msGoodsIds.length <= 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        int affectRows = goodsAdminService.delMsGoods(msGoodsIds);
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.DEL_ACTION_FAIL);
        }
        return ResultUtils.success("删除成功");
    }


    /**
     * 根据id获取一条秒杀活动
     * @param msGoodsId
     * @return
     */
    @GetMapping("/admin/getMsGoods/{msGoodsId}")
    public Result getMsActivityById(@PathVariable("msGoodsId") Integer msGoodsId){
        // 参数校验
        if (msGoodsId == null || msGoodsId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        // 获取数据
        Optional<TbMsGoods> msActivityById = Optional.ofNullable(goodsAdminService.getMsActivityById(msGoodsId));
        if (!msActivityById.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.MS_GOODS_NOT_FOUNT);
        }
        // bean转换
        MsGoodsBaseVO msGoodsBaseVO = new MsGoodsBaseVO();
        TbMsGoods tbMsGoods = msActivityById.get();
        BeanUtils.copyProperties(tbMsGoods, msGoodsBaseVO);
        msGoodsBaseVO.setMsPrice(tbMsGoods.getMsPrice().toString());
        msGoodsBaseVO.setStartTime(TimeUtils.date2String(tbMsGoods.getStartTime()));
        msGoodsBaseVO.setEndTime(TimeUtils.date2String(tbMsGoods.getEndTime()));
        msGoodsBaseVO.setGoodsStock(tbMsGoods.getTbGoods().getGoodsStock());
        return ResultUtils.success(msGoodsBaseVO);
    }


    /**
     * 修改秒杀活动接口
     * @param msGoodsBaseDTO
     * @return
     */
    @PostMapping("/admin/editMsGoods")
    public Result updateMsActivity(@RequestBody @Valid MsGoodsBaseDTO msGoodsBaseDTO) {
        int affectRows = 0;
        try{
            affectRows = goodsAdminService.updateMsActivity(msGoodsBaseDTO);
            redisUtils.del("msGoods:" + msGoodsBaseDTO.getMsGoodsId());
            redisUtils.del("stock:" + msGoodsBaseDTO.getMsGoodsId());
            int second = TimeUtils.calSecondString(msGoodsBaseDTO.getEndTime());
            redisUtils.setWithTime("stock:" + msGoodsBaseDTO.getMsGoodsId(), msGoodsBaseDTO.getMsGoodsStock().toString(), second + 3600);
            goodsAdminService.msGoodsAddRedis(msGoodsBaseDTO.getMsGoodsId());
        }catch (ParseException e){
            affectRows = 0;
            logger.warn("日期转换出错");
        }
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        return ResultUtils.success("修改成功");
    }


    /**
     * 上传商品图片到oss
     * @return
     */
    @PostMapping("/admin/uploadImg")
    public Result uploadGoodsImg(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()){
            throw new AppRuntimeException(ResultErrEnum.IMG_IS_EMPTY);
        }
        if (!FileUtils.checkImgType(multipartFile)){
            throw new AppRuntimeException(ResultErrEnum.ERROR_IMG_TYPE);
        }
        String res = goodsAdminService.imgUpload(multipartFile);
        if ("false".equals(res)){
            throw new AppRuntimeException(ResultErrEnum.IMG_UPLOAD_FAIL);
        }
        return ResultUtils.success("上传成功", res);
    }

    /**
     * 编辑商品信息
     * @param editGoodsDTO
     * @return
     */
    @PostMapping("/admin/editGoods")
    public Result editGoods(@RequestBody @Valid EditGoodsDTO editGoodsDTO){
        int affectRows = goodsAdminService.updateGoods(editGoodsDTO);
        if (affectRows <= 0 ){
            throw new AppRuntimeException(ResultErrEnum.MOD_ACTION_FAIL);
        }
        return ResultUtils.success("修改成功");
    }
}
