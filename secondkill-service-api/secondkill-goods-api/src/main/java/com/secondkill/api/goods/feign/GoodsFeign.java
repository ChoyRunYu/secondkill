package com.secondkill.api.goods.feign;

import com.secondkill.api.goods.vo.MsGoodsDetailVO;
import com.secondkill.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;


/**
 * 调用商品服务rpc
 */
@FeignClient(value = "secondkill-goods")
public interface GoodsFeign {

    @GetMapping("/goods/rpc/msGoods/detail/{msGoodsId}")
    Result<MsGoodsDetailVO> getMsGoods(@PathVariable("msGoodsId") Integer msGoodsId);
}
