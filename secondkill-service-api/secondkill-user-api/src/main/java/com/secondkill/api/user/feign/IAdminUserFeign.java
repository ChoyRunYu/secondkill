package com.secondkill.api.user.feign;


import com.secondkill.api.user.vo.AdminUserVo;
import com.secondkill.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

/**
 * @author choy
 * @date 2021/03/04
 * 管理员调用用户微服务feign组件
 */
@FeignClient(value = "secondkill-user")
public interface IAdminUserFeign {

    /**
     * 管理员登录调用接口
     * @param reqMap
     * @return
     */
    @PostMapping("/user/admin/login")
    Result<AdminUserVo> adminLogin(@RequestBody Map<String, String> reqMap);
}
