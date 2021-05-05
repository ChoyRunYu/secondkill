package com.secondkill.system.user.service;

import com.secondkill.api.user.dto.UserDTO;
import com.secondkill.api.user.entry.TbUser;
import com.secondkill.api.user.vo.UserVO;

/**
 * @author cairunyu
 * @date 2021/02/13
 * 用户service层
 */
public interface UserService {

    /**
     * 用户登录操作
     * @param username
     * @param password
     * @return
     */
    TbUser login(String username, String password);

    /**
     * 用户注册操作
     * @param tbUser
     * @return
     */
    int register(TbUser tbUser);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    TbUser getUserInfo(Integer id);

    /**
     * 检验用户名是否已经注册
     * @param username
     * @return
     */
    int checkUsername(String username);


    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    int register(String username, String password);
}
