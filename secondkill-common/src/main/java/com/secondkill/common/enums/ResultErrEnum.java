package com.secondkill.common.enums;

/**
 * @author Choy
 * @date 2021/03/01
 * 错误代码枚举类
 */
public enum ResultErrEnum {


    NOT_FOUND_ERROR(40004, "找不到资源"),
    NOT_MS_GOODS(40001, "暂时没有秒杀商品"),
    MS_GOODS_IS_END(40002, "抱歉，该商品已经下架"),
    MS_GOODS_NOT_FOUNT(40003, "抱歉，找不到该商品"),

    /**
     * 系统类型错误码
     */
    SYSTEM_ERROR(50000, "系统错误，请稍后再试"),
    EMPTY_PARAM_ERROR(50001, "参数不能为空"),
    ERR_PAGE_PARAM(50002, "错误的分页参数"),
    INVALID_PARAM(50003, "无效的参数"),
    DEL_ACTION_FAIL(50004, "删除失败"),
    MOD_ACTION_FAIL(50005, "修改失败"),
    ADD_ACTION_FAIL(50006, "添加失败"),
    CACHE_ACTION_FAIL(50007, "缓存失败"),
    IMG_IS_EMPTY(50008, "图片为空，请检查图片"),
    ERROR_IMG_TYPE(50009, "图片格式错误，请检查图片"),
    IMG_UPLOAD_FAIL(50010, "上传失败,请重新上传"),
    ORDER_IS_PAY(50011, "订单已支付"),
    ORDER_IS_EXPIRED(50012, "订单已过期"),

    /**
     * 用户类型错误码
     */
    USER_NOT_FOUND(10001, "帐号或密码错误"),
    EMPTY_TOKEN(10002, "token为空"),
    ERR_TOKEN(10003, "token解析错误，请重新登录"),
    EXPIRED_TOKEN(10004, "token已过期，请重新登录"),
    ERR_PERMISSION(10005, "权限不足"),
    USER_IS_REGISTER(10006, "该用户名已被注册"),
    USER_REGISTER_FAIL(10007, "注册失败"),



    ;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 枚举类构造函数
     * @param code
     * @param msg
     */
    ResultErrEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
