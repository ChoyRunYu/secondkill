package com.secondkill.system.user.mapper;

import com.secondkill.api.user.entry.TbAdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author Choy
 * @date 2021/03/03
 * 管理员用户mapper层
 */
public interface TbAdminUserMapper {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    TbAdminUser login(@Param("username") String username, @Param("password") String password);


    /**
     * 根据管理员id获取管理员信息
     * @param id
     * @return
     */
    TbAdminUser getAdminInfoById(Integer id);

}
