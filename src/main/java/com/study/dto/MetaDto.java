package com.study.dto;

import com.study.pojo.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:41
 * @Description: 标签、分类列表  dto:数据传输对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors//开启链式调用
public class MetaDto extends Meta {
    private int count;
}
