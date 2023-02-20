package com.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * @Classname: Response
 * @Date: 9/5/2021 8:13 下午
 * @Author: garlam
 * @Description:
 */


public class Response<T> implements Serializable {
    private static final Logger log = LogManager.getLogger(Response.class.getName());
    private static final long serialVersionUID = 1L;

    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "success";
    private static final int ERROR_CODE = 500;
    private static final String ERROR_MSG = "服务器内部异常，请联系技术人员"; //将error改成了内容信息

    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 200;
    public static final int FAIL = 500;
    public static final int NO_PERMISSION = 2;

    private String msg = "success";
    private int code = SUCCESS;
    private T data;

    public Response() {
        super();
    }

    public Response(T data) {
        super();
        this.data = data;
    }

    public Response(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public Response(int code, T data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Response(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Response(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public static Response success() {
        Response resp = new Response();
        resp.code = (SUCCESS_CODE);
        resp.msg = (SUCCESS_MSG);
        return resp;
    }

    public static Response successResponse(String msg) {
        Response resp = new Response();
        resp.code = SUCCESS_CODE;
        resp.msg = msg;
        return resp;
    }

    public static Response error() {
        Response resp = new Response();
        resp.code = (ERROR_CODE);
        resp.msg = (ERROR_MSG);
        return resp;
    }

    public static Response errorResponse(String msg) {
        Response resp = new Response();
        resp.code = ERROR_CODE;
        resp.msg = msg;
        return resp;
    }

    public static Response response(int code, String msg) {
        Response resp = new Response();
        resp.code = (code);
        resp.msg = (msg);
        return resp;
    }

    public static Response response(int code, String msg, Object data) {
        Response resp = new Response();
        resp.code = (code);
        resp.msg = (msg);
        resp.data = data;
        return resp;
    }

    public static Response success(Object data) {
        Response resp = new Response();
        resp.code = (SUCCESS_CODE);
        resp.msg = (SUCCESS_MSG);
        resp.data = data;
        return resp;
    }

    public static Response error(Object data) {
        Response resp = new Response();
        resp.code = (ERROR_CODE);
        resp.msg = (ERROR_MSG);
        resp.data = data;
        return resp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}

