package com.secondkill.api.user.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户dto类
 * @author choy
 * @date 2021/03/01
 */
public class UserDTO {

    @NotNull
    private Integer id;
    @NotEmpty(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "昵称不能为空")
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    @NotEmpty(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "手机不能为空")
    @NotBlank(message = "手机不能为空")
    private String phone;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
