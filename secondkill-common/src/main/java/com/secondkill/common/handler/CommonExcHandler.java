package com.secondkill.common.handler;

import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.Result;
import com.secondkill.common.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Choy
 * @date 2021/03/01
 * 异常处理类
 */
@ControllerAdvice
public class CommonExcHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonExcHandler.class);


    /**
     * 异常处理方法
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AppRuntimeException.class)
    public Result baseServiceException(AppRuntimeException e){
        int code = e.getCode();
        String msg = e.getMessage();
        logger.error("errorCode: {}, errorMessage: {}", code, msg);
        return ResultUtils.error(code, msg);
    }

    /**
     * 处理参数校验错误异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidatedException(MethodArgumentNotValidException e){
        int code = 50001;
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        logger.error("errorCode: {}, errorMessage: {}", code, msg);
        return ResultUtils.error(code, msg);
    }
}
