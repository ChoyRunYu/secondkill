package com.secondkill.auth.service;

import java.util.HashMap;

/**
 * @author Choy
 * @date 2021/03/03
 * 用户鉴权Service类
 */
public interface AuthService {

    /**
     * 普通用户登录
     * @return
     */
    String userAuthLogin(HashMap<String, String> reqMap);

    /**
     * 管理员用户登录
     * @return
     */
    String adminAuthLogin(HashMap<String, String> reqMap);

    /**
     * token校验
     * @return
     */
    String tokenVerity(String token);


    Boolean userLogout();

    Boolean adminLogout();
}
