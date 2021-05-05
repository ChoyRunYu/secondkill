package com.secondkill.system.user.mapper;

import com.secondkill.api.user.entry.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author cairunyu
 * @date 2021/02/13
 * 用户dao层
 */
public interface TbUserMapper {

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    TbUser getUserById(Integer id);

    /**
     * 添加用户
     *
     * @param tbUser
     * @return
     */
    int insertUser(TbUser tbUser);

    /**
     * 修改用户
     *
     * @param tbUser
     * @return
     */
    int updateUser(TbUser tbUser);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    TbUser login(@Param("username") String username, @Param("password") String password);

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    int userRegister(TbUser tbUser);

    /**
     * 分页列出商城所有会员，一次返回10条记录
     * @return
     */
    List<TbUser> listUser(@Param("page")Integer page, @Param("total") Integer total);

    /**
     * 统计用户数
     * @return
     */
    int countUser();

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delUser(Integer id);

    /**
     * 启用/停用商城会员
     * @param id
     * @param isEnable
     * @return
     */
    int updateUserIsEnable(@Param("id")Integer id, @Param("isEnable")Integer isEnable);

    /**
     * 检验用户名是否已注册
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    int register(TbUser tbUser);

}
