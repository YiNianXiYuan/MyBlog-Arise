package com.study.dao;

import com.study.pojo.Option;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:57
 * @Description: 网站选项相关Dao接口
 */
@Mapper
public interface OptionDao {
    /**
     * 获取网站全部信息
     * @return
     */
    List<Option> getOptions();

    /**
     * 更新网站配置
     * @param option
     */
    void updateOptionByName(Option option);

    /**
     * 通过名称网站配置
     * @param name
     * @return
     */
    Option getOptionByName(String name);
}
