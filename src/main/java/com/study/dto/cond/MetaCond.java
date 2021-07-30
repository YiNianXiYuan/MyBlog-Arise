package com.study.dto.cond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:36
 * @Description: 评论的查找参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors//开启链式调用
public class MetaCond  {
    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;
}
