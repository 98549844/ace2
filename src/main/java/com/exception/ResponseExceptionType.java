package com.exception;

/**
 * @Classname: CustomExceptionType
 * @Date: 9/11/2021 11:24 下午
 * @Author: garlam
 * @Description:
 */
public enum ResponseExceptionType {

    USER_INPUT_ERROR(400,"用户输入异常"),
    PAGE_NOT_FOUND_ERROR(404,"未找到页面异常"),
    SYSTEM_ERROR (500,"系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");

    ResponseExceptionType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }

    private String typeDesc;//异常类型中文描述

    private int code; //code

    public String getTypeDesc() {
        return typeDesc;
    }

    public int getCode() {
        return code;
    }
}
