package com.secondkill.auth.controller;


import com.secondkill.auth.service.AuthService;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.JwtUtils;
import com.secondkill.common.utils.Result;
import com.secondkill.common.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * @author Choy
 * @date 2021/03/03
 * 权限中心控制器
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 管理员登录授权
     * @return
     */
    @PostMapping("/auth/admin")
    public Result adminAuth(@RequestBody HashMap<String, String> reqMap){
        String username = reqMap.get("username");
        String password = reqMap.get("password");
        if (username == null || "".equals(username)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        if (password == null || "".equals(password)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        String token = authService.adminAuthLogin(reqMap);
        HashMap<String, String> resMap = new HashMap<>(1);
        resMap.put("token", token);
        return ResultUtils.success("登录成功", resMap);
    }


    /**
     * 普通用户登录授权
     * @param reqMap
     * @return
     */
    @PostMapping("/auth/user")
    public Result userAuth(@RequestBody HashMap<String, String> reqMap){
        String username = reqMap.get("username");
        String password = reqMap.get("password");
        if (username == null || "".equals(username)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        if (password == null || "".equals(password)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        String token = authService.userAuthLogin(reqMap);
        HashMap<String, String> resMap = new HashMap<>(1);
        resMap.put("token", token);
        return ResultUtils.success("登录成功", resMap);
    }


    /**
     * 校验token
     * @param request
     * @return
     */
    @PostMapping("/auth/token")
    public Result checkToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token == null || "".equals(token)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_TOKEN);
        }
        String flag = jwtUtils.verityToken(token);
        if("false".equals(flag)){
            throw new AppRuntimeException(ResultErrEnum.ERR_TOKEN);
        }else if ("expired".equals(flag)){
            throw new AppRuntimeException(ResultErrEnum.EXPIRED_TOKEN);
        }
        return ResultUtils.success("校验成功");
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @PostMapping("/auth/logout")
    public Result logout(HttpServletRequest request){
        String token= request.getHeader("Authorization");
        if (token == null || "".equals(token)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_TOKEN);
        }
        return ResultUtils.success("退出成功");
    }
}
