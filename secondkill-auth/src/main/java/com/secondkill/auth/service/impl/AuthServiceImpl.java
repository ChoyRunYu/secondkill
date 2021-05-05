package com.secondkill.auth.service.impl;

import com.secondkill.api.user.feign.IAdminUserFeign;
import com.secondkill.api.user.feign.IUserFeign;
import com.secondkill.api.user.vo.AdminUserVo;
import com.secondkill.api.user.vo.UserVO;
import com.secondkill.auth.service.AuthService;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.JwtUtils;
import com.secondkill.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Choy
 * @date 2021/03/01
 * 权限校验service
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private IUserFeign userFeign;
    @Autowired
    private IAdminUserFeign adminUserFeign;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 普通用户登录
     * @param reqMap 传入的登录参数
     * @return
     */
    @Override
    public String userAuthLogin(HashMap<String, String> reqMap) {
        String sign = "false";
        Result<UserVO> userVOResult = userFeign.userLogin(reqMap);
        if (userVOResult.getCode() != 20000){
            logger.warn("{}用户登录失败", reqMap.get("username"));
            throw new AppRuntimeException(ResultErrEnum.USER_NOT_FOUND);
        }else{
            UserVO userVO = userVOResult.getData();
            sign = jwtUtils.generateToken(userVO, "false");
        }
        logger.info("{}用户登录成功", reqMap.get("username"));
        return sign;
    }

    /**
     * 管理员用户登录
     * @param reqMap 传入的登录参数
     * @return
     */
    @Override
    public String adminAuthLogin(HashMap<String, String> reqMap) {
        String sign = "false";
        Result<AdminUserVo> adminUserVoResult = adminUserFeign.adminLogin(reqMap);
        if (adminUserVoResult.getCode() != 20000){
            logger.warn("{}管理员登录失败", reqMap.get("username"));
            throw new AppRuntimeException(ResultErrEnum.USER_NOT_FOUND);
        }else{
            AdminUserVo adminUserVo = adminUserVoResult.getData();
            sign = jwtUtils.generateToken(adminUserVo, "true");
        }
        logger.info("{}管理员登录成功", reqMap.get("username"));
        return sign;
    }

    /**
     * token校验
     * @param token 颁发的token
     * @return
     */
    @Override
    public String tokenVerity(String token) {
        return jwtUtils.verityToken(token);
    }

    @Override
    public Boolean userLogout() {
        return null;
    }

    @Override
    public Boolean adminLogout() {
        return null;
    }
}
