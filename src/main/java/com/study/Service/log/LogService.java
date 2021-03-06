package com.study.Service.log;

import com.github.pagehelper.PageInfo;
import com.study.pojo.Log;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:23
 * @Description: 日志相关Service接口
 */
public interface LogService {
    /**
     * 添加日志
     * @param action    触发动作
     * @param data      产生数据
     * @param ip        产生IP
     * @param authorId  产生人
     */
    void addLog(String action, String data, String ip, Integer authorId);

    /**
     * 获取日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Log> getLogs(int pageNum, int pageSize);
}
