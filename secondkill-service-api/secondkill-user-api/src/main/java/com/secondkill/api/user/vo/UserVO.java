package com.secondkill.api.user.vo;

import com.secondkill.api.user.entry.TbUser;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;

import java.util.Date;

/**
 * 用户Vo类
 * @Author choy
 * @date 2021/03/01
 */
public class UserVO {

    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String phone;
    private String createTime;
    private Integer isEnable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
