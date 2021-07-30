package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:38
 * @Description: 公共属性的类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors//开启链式调用
public class BaseDto {
    /**
     * 用户名
     */
    private String userName;
}
