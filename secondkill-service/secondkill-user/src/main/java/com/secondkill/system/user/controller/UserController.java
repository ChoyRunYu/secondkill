package com.secondkill.system.user.controller;


import com.secondkill.api.user.entry.TbUser;
import com.secondkill.api.user.vo.UserInfoVO;
import com.secondkill.api.user.vo.UserVO;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.*;
import com.secondkill.system.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author choy
 * @date 2021/02/13
 * 用户接口
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户登录接口
     * @param reqMap
     * @return
     */
    @PostMapping("/user/login")
    public Result login(
            @RequestBody Map<String, String> reqMap){
        String username = reqMap.get("username");
        String password = reqMap.get("password");
        // 参数校验
        if (username == null || "".equals(username)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        if (password == null || "".equals(password)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        // 查询数据库
        Optional<TbUser> userVO1 = Optional.ofNullable(userService.login(username, password));
        if (!userVO1.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.USER_NOT_FOUND);
        }
        // bean 拷贝
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userVO1.get(), userVO);
        return ResultUtils.success("登录成功", userVO);
    }

    /**
     * 根据用户id获取用户信息
     * @return
     */
    @GetMapping("/user/info")
    public Result getUserInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Integer tokenUserId = jwtUtils.getTokenUserId(token);
        Optional<TbUser> userInfo = Optional.ofNullable(userService.getUserInfo(tokenUserId));
        if (!userInfo.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo.get(), userInfoVO);
        userInfoVO.setCreateTime(TimeUtils.date2String(userInfo.get().getCreateTime()));
        return ResultUtils.success(userInfoVO);
    }


    /**
     * 检验用户名是否已注册
     * @return
     */
    @GetMapping("/user/check/{username}")
    public Result checkUsername(@PathVariable("username")String username){
        if (StringUtils.isEmpty(username)){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        if (userService.checkUsername(username) > 0){
            return ResultUtils.success("该用户名已被注册", false);
        }
        return ResultUtils.success("可以注册", true);
    }

    /**
     * 注册接口
     * @return
     */
    @PostMapping("/user/register")
    public Result register(@RequestBody HashMap<String, String> reqMap){
        String username = reqMap.get("username");
        String password = reqMap.get("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        if(userService.register(username, password) != 1){
            throw new AppRuntimeException(ResultErrEnum.USER_REGISTER_FAIL);
        }
        return ResultUtils.success("注册成功");
    }


}
