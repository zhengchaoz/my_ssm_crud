package com.ssm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 */
public class Msg {

    public static final int BUST = 200;

    public static final int SUCCESS = 100;

    private int code;

    private String msg;

    private Map<String, Object> extend = new HashMap<>();

    public Msg() {
    }

    public Msg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public static Msg success() {
        return new Msg(SUCCESS, "处理成功！");
    }

    public static Msg bust() {
        return new Msg(BUST, "处理失败！");
    }

    public Msg put(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}
