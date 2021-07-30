package com.study.utils;

import com.google.gson.Gson;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:53
 * @Description: json转换工具
 */
public class GsonUtils {
    private static final Gson gson = new Gson();

    public static String toJsonString(Object object) {
        return object == null ? null : gson.toJson(object);
    }
}
