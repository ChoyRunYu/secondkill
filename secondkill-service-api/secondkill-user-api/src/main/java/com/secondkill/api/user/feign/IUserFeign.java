package com.secondkill.api.user.feign;


import com.secondkill.api.user.vo.UserVO;
import com.secondkill.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;


/**
 * @author choy
 * @date 2021/03/04
 * 用户微服务调用feign
 */
@FeignClient("secondkill-user")
public interface IUserFeign {

    /**
     * 调用微服务获取用户信息
     * @return
     */
    @GetMapping("/user/test")
    String login();

    /**
     * 普通用户登录调用服务接口
     * @param reqMap
     * @return
     */
    @PostMapping(value = "/user/login")
    Result<UserVO> userLogin(@RequestBody HashMap<String, String> reqMap);
}
