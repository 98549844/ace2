package com.api;


import com.constant.MessageConstants;

public class ApiResponse<T>  {
    private Boolean success = true;
    private String message = MessageConstants.MESSAGE_SUCCESS;
    private String code = MessageConstants.CODE_SUCCESS;
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(Boolean success, String message, String code) {
        super();
        this.success = success;
        this.message = message;
        this.code = code;
    }
}
