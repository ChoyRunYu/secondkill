package com.secondkill.system.goods.rpc;


import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.api.goods.vo.MsGoodsDetailVO;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.Result;
import com.secondkill.common.utils.ResultUtils;
import com.secondkill.common.utils.TimeUtils;
import com.secondkill.system.goods.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;


/**
 * 微服务rpc调用接口
 * @author choy
 * @date 2021/03/28
 */
@RestController
@RequestMapping("/goods")
public class RpcGoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取秒杀商品详情信息
     * @param msGoodsId
     * @return
     */
    @GetMapping("/rpc/msGoods/detail/{msGoodsId}")
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
        msGoodsDetailVO.setGoodsId(tbMsGoods.getTbGoods().getGoodsId());
        msGoodsDetailVO.setStartTime(TimeUtils.date2String(tbMsGoods.getStartTime()));
        msGoodsDetailVO.setEndTime(TimeUtils.date2String(tbMsGoods.getEndTime()));
        msGoodsDetailVO.setGoodsImg(tbMsGoods.getTbGoods().getGoodsImg());
        msGoodsDetailVO.setGoodsTitle(tbMsGoods.getTbGoods().getGoodsTitle());
        msGoodsDetailVO.setMsPrice(tbMsGoods.getMsPrice().toString());
        msGoodsDetailVO.setGoodsPrice(tbMsGoods.getTbGoods().getGoodsPrice().toString());
        return ResultUtils.success(msGoodsDetailVO);
    }
}
