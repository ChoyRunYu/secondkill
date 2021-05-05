package com.secondkill.common.utils;


/**
 * @author Choy
 * @date 2021/03/01
 * 全局统一返回结果工具类
 */
public class ResultUtils {

    /**
     * 返回请求成功信息,包含数据
     * @param data
     * @return
     */
    public static Result success(Object data){
        return new Result(20000, "请求成功", data);
    }

    /**
     * 返回请求成功，不包含数据
     * @return
     */
    public static Result success(){
        return new Result(20000, "请求成功");
    }

    /**
     * 返回请求成功，不包含数据，自定义信息
     * @param msg
     * @return
     */
    public static Result success(String msg){
        return new Result(20000, msg);
    }

    /**
     * 返回自定义请求成功操作
     * @param msg
     * @param data
     * @return
     */
    public static Result success(String msg, Object data){
        return new Result(20000, msg, data);
    }

    /**
     * 返回失败的自定义json数据，不包含data
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg){
        return new Result(code, msg);
    }


}
