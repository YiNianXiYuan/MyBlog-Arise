package com.study.utils;

import com.study.pojo.Meta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:49
 * @Description: 后台公共函数
 */
@Component
public final class AdminCommons {
    /**
     * 判断category和cat的交集
     * @param category
     * @param cats
     * @return
     */
    public static boolean exist_cat(Meta category, String cats) {
        String[] arr = StringUtils.split(cats, ",");
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 定义颜色样式数组
     */
    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 随机样式
     * @return
     */
    public static String rand_color() {
        int r = Tools.rand(0, COLORS.length - 1);
        return COLORS[r];
    }
}