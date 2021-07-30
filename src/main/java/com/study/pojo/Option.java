package com.study.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:18
 * @Description: 网站配置项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors//开启链式调用
public class Option {
    /**
     * 名称
     */
    private String name;
    /**
     * 内容
     */
    private String value;

    /**
     * 描述
     */
    private String description;
}
