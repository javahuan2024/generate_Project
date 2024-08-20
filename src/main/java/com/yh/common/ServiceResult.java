package com.yh.common;

import java.io.Serializable;



public final class ServiceResult<T> implements Serializable {
    private static final long serialVersionUID = 6977558218691386450L;
    private boolean succeed = true;
    private int code = -1;
    private String msg;
    private T data;

    public ServiceResult() {
    }

    public ServiceResult(T data) {
        this.data = data;
    }

    public ServiceResult(boolean succeed, int code, String msg) {
        this.succeed = succeed;
        this.code = code;
        this.msg = msg;
    }

    public ServiceResult(boolean succeed, T data, String msg) {
        this.succeed = succeed;
        this.data = data;
        this.msg = msg;
    }

    public ServiceResult(boolean succeed, T data, int code, String msg) {
        this.succeed = succeed;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ServiceResult(boolean succeed, String msg) {
        this.succeed = succeed;
        this.msg = msg;
    }

    public boolean isSucceed() {
        return this.succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
