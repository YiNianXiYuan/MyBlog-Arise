package com.study.Service.log.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.Service.log.LogService;
import com.study.dao.LogDao;
import com.study.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:23
 * @Description: 日志相关Service接口实现
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;


    @Override
    public void addLog(String action, String data, String ip, Integer authorId) {
        Log logDomain = new Log();
        logDomain.setAuthorId(authorId);
        logDomain.setIp(ip);
        logDomain.setData(data);
        logDomain.setAction(action);
        logDao.addLog(logDomain);
    }

    @Override
    public PageInfo<Log> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Log> logs = logDao.getLogs();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        return pageInfo;

    }
}
