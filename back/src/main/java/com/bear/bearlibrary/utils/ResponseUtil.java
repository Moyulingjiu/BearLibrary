package com.bear.bearlibrary.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", ReturnNo.OK.getCode());
        obj.put("msg", ReturnNo.OK.getMessage());
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", ReturnNo.OK.getCode());
        obj.put("msg", ReturnNo.OK.getMessage());
        obj.put("data", data);
        return obj;
    }

    public static Object fail(ReturnNo code) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", code.getCode());
        obj.put("msg", code.getMessage());
        return obj;
    }

    public static Object fail(ReturnNo code, String msg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", code.getCode());
        obj.put("msg", msg);
        return obj;
    }

    public static Object fail(ReturnNo code, String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", code.getCode());
        obj.put("msg", msg);
        obj.put("data", data);
        return obj;
    }
}
