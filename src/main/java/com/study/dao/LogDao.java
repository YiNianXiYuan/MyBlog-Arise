package com.study.dao;

import com.study.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:55
 * @Description: 日志dao层接口
 */
@Mapper
public interface LogDao {
    /**
     * 添加日志
     * @param logDomain
     * @return
     */
    int addLog(Log logDomain);

    /**
     * 获取日志
     * @return
     */
    List<Log> getLogs();
}
