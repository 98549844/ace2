package com.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: CustomException
 * @Date: 9/11/2021 11:20 下午
 * @Author: garlam
 * @Description:
 */


public class ResponseException extends RuntimeException{
    private static Logger log = LogManager.getLogger(ResponseException.class.getName());

    //异常错误编码
    private int code ;
    //异常信息
    private String message;


    public ResponseException(ResponseExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}

