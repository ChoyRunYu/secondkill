package com.secondkill.system.user.service.impl;

import com.secondkill.api.user.dto.UserDTO;
import com.secondkill.api.user.entry.TbAdminUser;
import com.secondkill.api.user.entry.TbUser;
import com.secondkill.common.utils.JwtUtils;
import com.secondkill.system.user.mapper.TbAdminUserMapper;
import com.secondkill.system.user.mapper.TbUserMapper;
import com.secondkill.system.user.service.UserAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author choy
 * @date 2021/03/01
 * 管理员用户管理模块service层
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private TbAdminUserMapper adminUserMapper;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 管理员登录操作
     * @param username
     * @param password
     * @return
     */
    @Override
    public TbAdminUser login(String username, String password) {
        return adminUserMapper.login(username, password);
    }

    /**
     * 根据用户id获取用户信息
     * @param token
     * @return
     */
    @Override
    public TbAdminUser getAdminInfoById(String token) {
        Integer tokenUserId = jwtUtils.getTokenUserId(token);
        return adminUserMapper.getAdminInfoById(tokenUserId);
    }

    /**
     * 分页返回商城会员列表
     * @param page
     * @return
     */
    @Override
    public List<TbUser> listUser(Integer page, Integer total) {
        return userMapper.listUser(page, total);
    }


    /**
     * 统计商城会员用户总数
     * @return
     */
    @Override
    public int countUser() {
        return userMapper.countUser();
    }

    /**
     * 添加新用户
     * @param tbUser
     * @return
     */
    @Override
    public Integer insertUser(TbUser tbUser) {
        tbUser.setIsEnable(0);
        tbUser.setCreateTime(new Date());
        return userMapper.insertUser(tbUser);
    }

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    @Override
    public Integer updateUser(UserDTO userDTO) {
        TbUser tbUser = new TbUser();
        BeanUtils.copyProperties(userDTO, tbUser);
        return userMapper.updateUser(tbUser);
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @Override
    public Integer delUser(Integer id) {
        return userMapper.delUser(id);
    }

    /**
     * 启用/停用商城会员帐号
     * @param id
     * @param isEnable
     * @return
     */
    @Override
    public Integer updateUserIsEnable(Integer id, Integer isEnable) {
        return userMapper.updateUserIsEnable(id, isEnable);
    }

    /**
     * 根据id获取会员信息
     * @param id
     * @return
     */
    @Override
    public TbUser getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
