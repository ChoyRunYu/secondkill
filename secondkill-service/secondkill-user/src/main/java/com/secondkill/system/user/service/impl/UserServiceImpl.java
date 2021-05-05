package com.secondkill.system.user.service.impl;

import com.secondkill.api.user.entry.TbUser;
import com.secondkill.api.user.vo.UserInfoVO;
import com.secondkill.system.user.mapper.TbUserMapper;
import com.secondkill.system.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @auhtor cairunyu
 * @date 2021/02/13
 * 用户service层实现类
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private TbUserMapper userMapper;


    /**
     * 用户登录操作
     * @param username
     * @param password
     * @return
     */
    @Override
    public TbUser login(String username, String password) {
        return userMapper.login(username, password);
    }

    /**
     * 用户注册操作
     * @param tbUser
     * @return
     */
    @Override
    public int register(TbUser tbUser) {
        tbUser.setCreateTime(new Date());
        tbUser.setIsEnable(0);
        return userMapper.userRegister(tbUser);
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Override
    public TbUser getUserInfo(Integer id) {
        return userMapper.getUserById(id);
    }


    /**
     * 检验用户名是否已注册
     * @param username
     * @return
     */
    @Override
    public int checkUsername(String username) {
        return userMapper.checkUsername(username);
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public int register(String username, String password) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername(username);
        tbUser.setPassword(password);
        tbUser.setCreateTime(new Date());
        tbUser.setNickname(username);
        return userMapper.register(tbUser);
    }
}
