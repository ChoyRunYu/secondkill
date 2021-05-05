package com.secondkill.system.user.service;

import com.secondkill.api.user.dto.UserDTO;
import com.secondkill.api.user.entry.TbAdminUser;
import com.secondkill.api.user.entry.TbUser;

import java.util.List;

/**
 * 用户管理模块service层
 * @author choy
 * @date 2021/03/01
 */
public interface UserAdminService {

    /**
     * 管理员登录操作
     * @param username
     * @param password
     * @return
     */
    TbAdminUser login(String username, String password);

    /**
     * 根据用户id获取管理员信息
     * @param token 传递的token
     * @return
     */
    TbAdminUser getAdminInfoById(String token);

    /**
     * 分页返回商城会员列表
     * @param page 页数
     * @param total 条数
     * @return
     */
    List<TbUser> listUser(Integer page, Integer total);

    /**
     * 统计商城会员用户总数
     * @return
     */
    int countUser();

    /**
     * 添加新用户
     * @param tbUser
     * @return
     */
    Integer insertUser(TbUser tbUser);

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    Integer updateUser(UserDTO userDTO);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer delUser(Integer id);

    /**
     * 启用/停用商城会员帐号
     * @param id
     * @param isEnable
     * @return
     */
    Integer updateUserIsEnable(Integer id, Integer isEnable);

    /**
     * 根据id获取会员信息
     * @param id
     * @return
     */
    TbUser getUserById(Integer id);
}
