package com.study.Service.option;

import com.study.pojo.Option;

import java.util.List;
import java.util.Map;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:26
 * @Description: 网站选项相关Service接口
 */
public interface OptionService {
    /**
     * 获取全部网站配置
     * @return
     */
    List<Option> getOptions();

    /**
     * 保存系统设置
     * @param querys
     */
    void saveOptions(Map<String,String> querys);

    /**
     * 更新网站配置
     * @param name
     * @param value
     */
    void updateOptionByName(String name, String value);

    /**
     * 通过名称获取网站配置
     * @param site_record
     * @return
     */
    Option getOptionByName(String site_record);
}
