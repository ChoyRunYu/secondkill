package com.secondkill.common.expection;

import com.secondkill.common.enums.ResultErrEnum;

/**
 * @author Choy
 * @date 2021/03/01
 * 公共异常处理类
 */
public class AppRuntimeException extends RuntimeException {

    private int code;

    /**
     * 根据枚举返回错误类型
     * @param resultErrEnum
     */
    public AppRuntimeException(ResultErrEnum resultErrEnum){
        super(resultErrEnum.getMsg());
        this.code = resultErrEnum.getCode();
    }

    /**
     * 自定义错误类型
     * @param code
     * @param message
     */
    public AppRuntimeException(Integer code, String message){
        super(message);
        this.code = code;
    }

    /**
     * 获取错误状态码
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 重写获取错误信息接口
     * @return
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
