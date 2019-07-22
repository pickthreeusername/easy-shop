package com.cyc.easy.shop.commons.dto;

import java.io.Serializable;

public class BaseResult implements Serializable {
    private int status;
    private String message;
    public final static int STATUS_SUCCESS = 200;
    public  final static int STATUS_FAIL = 500;

    public static BaseResult success() {
        return createResult(STATUS_SUCCESS, "响应成功");
    }

    public static BaseResult success(String message) {
        return createResult(STATUS_SUCCESS, message);
    }

    public static BaseResult fail() {
        return createResult(STATUS_FAIL, "操作失败");
    }
    public static BaseResult fail(String message) {
        return createResult(STATUS_FAIL, message);
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

    public static BaseResult createResult(int status, String message) {
        BaseResult result = new BaseResult();
        result.setStatus(status);
        result.setMessage(message);
        return result;
    }
}
