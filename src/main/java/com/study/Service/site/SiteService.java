package com.study.Service.site;

import com.study.dto.StatisticsDto;
import com.study.pojo.Comment;
import com.study.pojo.Content;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:30
 * @Description: 网站相关Service接口
 */
public interface SiteService {
    /**
     * 获取评论列表
     * @param limit
     * @return
     */
    List<Comment> getComments(int limit);

    /**
     * 获取文章列表
     * @param limit
     * @return
     */
    List<Content> getNewArticles(int limit);

    /**
     * 获取后台统计数
     * @return
     */
    StatisticsDto getStatistics();
}
