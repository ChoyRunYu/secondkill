package com.secondkill.system.goods.controller;

import com.secondkill.api.goods.dto.MsGoodsDTO;
import com.secondkill.api.goods.entry.TbGoods;
import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.api.goods.vo.MsGoodsDetailVO;
import com.secondkill.api.goods.vo.MsGoodsListVO;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.Result;
import com.secondkill.common.utils.ResultUtils;
import com.secondkill.common.utils.TimeUtils;
import com.secondkill.system.goods.service.GoodsService;
import org.bouncycastle.pqc.crypto.rainbow.RainbowSigner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auhtor choy
 * @date 2021/03/11
 * 商品api控制器接口
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取秒杀商品列表
     * @return
     */
    @GetMapping("/listMsGoods")
    public Result listMsGoods(){
        Optional<List<TbMsGoods>> optionalTbGoodsList = Optional.ofNullable(goodsService.listMsGoods());
        if (!optionalTbGoodsList.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.NOT_MS_GOODS);
        }
        List<MsGoodsListVO> msGoodsListVOS = new ArrayList<>();
        for (TbMsGoods tbMsGoods : optionalTbGoodsList.get()) {
            MsGoodsListVO msGoodsListVO = new MsGoodsListVO();
            msGoodsListVO.setGoodsImg(tbMsGoods.getTbGoods().getGoodsImg());
            msGoodsListVO.setGoodsTitle(tbMsGoods.getTbGoods().getGoodsTitle());
            msGoodsListVO.setMsGoodsId(tbMsGoods.getMsGoodsId());
            msGoodsListVO.setGoodsPrice(tbMsGoods.getTbGoods().getGoodsPrice().toString());
            msGoodsListVO.setMsPrice(tbMsGoods.getMsPrice().toString());
            msGoodsListVO.setEndTime(TimeUtils.date2String(tbMsGoods.getEndTime()));
            msGoodsListVO.setStartTime(TimeUtils.date2String(tbMsGoods.getStartTime()));
            msGoodsListVO.setMsGoodsStock(tbMsGoods.getMsGoodsStock());
            msGoodsListVOS.add(msGoodsListVO);
        }
        HashMap<String, List<MsGoodsListVO>> reqMap = new HashMap<>();
        reqMap.put("msGoodsList", msGoodsListVOS);
        return ResultUtils.success(reqMap);
    }

    /**
     * 获取秒杀商品详情信息
     * @param msGoodsId
     * @return
     */
    @GetMapping("/msGoods/detail/{msGoodsId}")
    public Result getMsGoodsDetail(@PathVariable("msGoodsId") Integer msGoodsId){
        if (msGoodsId == 0 || msGoodsId == null){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        Optional<TbMsGoods> msGoodsById = Optional.ofNullable(goodsService.getMsGoodsById(msGoodsId));
        if (!msGoodsById.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.MS_GOODS_NOT_FOUNT);
        }
        TbMsGoods tbMsGoods = msGoodsById.get();
        MsGoodsDetailVO msGoodsDetailVO = new MsGoodsDetailVO();
        BeanUtils.copyProperties(tbMsGoods, msGoodsDetailVO);
        msGoodsDetailVO.setStartTime(TimeUtils.date2String(tbMsGoods.getStartTime()));
        msGoodsDetailVO.setEndTime(TimeUtils.date2String(tbMsGoods.getEndTime()));
        msGoodsDetailVO.setGoodsImg(tbMsGoods.getTbGoods().getGoodsImg());
        msGoodsDetailVO.setGoodsTitle(tbMsGoods.getTbGoods().getGoodsTitle());
        msGoodsDetailVO.setMsPrice(tbMsGoods.getMsPrice().toString());
        msGoodsDetailVO.setGoodsPrice(tbMsGoods.getTbGoods().getGoodsPrice().toString());
        HashMap<String, Object> resMap = new HashMap<>(2);
        resMap.put("goodsDetail", msGoodsDetailVO);
        resMap.put("serverTime", TimeUtils.date2String(new Date()));
        return ResultUtils.success(resMap);
    }


    /**
     * 搜索商品，和获取秒杀商品列表拆成两个接口
     * 也是为了方便以后可以上elasticSearch
     * @return
     */
    @GetMapping("/listMsGoods/{keyword}")
    public Result listMsGoods(@PathVariable("keyword") String keyword){
        if (StringUtils.isEmpty(keyword)){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        Optional<List<TbMsGoods>> optionalTbGoodsList = Optional.ofNullable(goodsService.listMsGoodsBySearch(keyword));
        if (!optionalTbGoodsList.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.NOT_MS_GOODS);
        }
        List<MsGoodsListVO> msGoodsListVOS = new ArrayList<>();
        for (TbMsGoods tbMsGoods : optionalTbGoodsList.get()) {
            MsGoodsListVO msGoodsListVO = new MsGoodsListVO();
            msGoodsListVO.setGoodsImg(tbMsGoods.getTbGoods().getGoodsImg());
            msGoodsListVO.setGoodsTitle(tbMsGoods.getTbGoods().getGoodsTitle());
            msGoodsListVO.setMsGoodsId(tbMsGoods.getMsGoodsId());
            msGoodsListVO.setGoodsPrice(tbMsGoods.getTbGoods().getGoodsPrice().toString());
            msGoodsListVO.setMsPrice(tbMsGoods.getMsPrice().toString());
            msGoodsListVO.setEndTime(TimeUtils.date2String(tbMsGoods.getEndTime()));
            msGoodsListVO.setStartTime(TimeUtils.date2String(tbMsGoods.getStartTime()));
            msGoodsListVO.setMsGoodsStock(tbMsGoods.getMsGoodsStock());
            msGoodsListVOS.add(msGoodsListVO);
        }
        HashMap<String, List<MsGoodsListVO>> reqMap = new HashMap<>();
        reqMap.put("msGoodsList", msGoodsListVOS);
        return ResultUtils.success(reqMap);
    }
}
