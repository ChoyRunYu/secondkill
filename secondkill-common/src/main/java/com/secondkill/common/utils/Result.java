package com.secondkill.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @auhtor choy
 * @date 2021/03/01
 * 返回json类
 */
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(){}

    public Result(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
