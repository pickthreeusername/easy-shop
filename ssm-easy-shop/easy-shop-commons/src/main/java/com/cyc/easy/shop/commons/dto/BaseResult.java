package com.cyc.easy.shop.commons.dto;

import java.io.Serializable;

public class BaseResult implements Serializable {
    public final static int STATUS_SUCCESS = 200;
    public  final static int STATUS_FAIL = 500;

    private int status;
    private String message;
    private Object data;


    public static BaseResult success() {
        return createResult(STATUS_SUCCESS, "响应成功", null);
    }

    public static BaseResult success(String message) {
        return createResult(STATUS_SUCCESS, message, null);
    }

    public static BaseResult success(String message, Object data) {
        return createResult(STATUS_SUCCESS, message, data);
    }
    public static BaseResult fail() {
        return createResult(STATUS_FAIL, "操作失败", null);
    }
    public static BaseResult fail(String message) {
        return createResult(STATUS_FAIL, message, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResult createResult(int status, String message, Object data) {
        BaseResult result = new BaseResult();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
