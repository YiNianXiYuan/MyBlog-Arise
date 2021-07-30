package com.study.Service.site.impl;

import com.github.pagehelper.PageHelper;
import com.study.Service.site.SiteService;
import com.study.constant.Types;
import com.study.dao.AttachDao;
import com.study.dao.CommentDao;
import com.study.dao.ContentDao;
import com.study.dao.MetaDao;
import com.study.dto.StatisticsDto;
import com.study.dto.cond.CommentCond;
import com.study.dto.cond.ContentCond;
import com.study.pojo.Comment;
import com.study.pojo.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:29
 * @Description: 网站相关Service接口实现
 */
@Service
public class SiteServiceImpl implements SiteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private MetaDao metaDao;

    @Autowired
    private AttachDao attAchDao;



    @Override
    @Cacheable(value = "siteCache", key = "'comments_' + #p0")
    public List<Comment> getComments(int limit) {
        LOGGER.debug("Enter recentComments method: limit={}", limit);
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        PageHelper.startPage(1,limit);
        List<Comment> rs = commentDao.getCommentsByCond(new CommentCond());
        LOGGER.debug("Exit recentComments method");
        return rs;
    }

    @Override
    @Cacheable(value = "siteCache", key = "'newArticles_' + #p0")
    public List<Content> getNewArticles(int limit) {
        LOGGER.debug("Enter recentArticles method:limit={}",limit);
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        PageHelper.startPage(1,limit);
        List<Content> rs = contentDao.getArticleByCond(new ContentCond());
        LOGGER.debug("Exit recentArticles method");
        return rs;
    }

    @Override
    @Cacheable(value = "siteCache", key = "'statistics_'")
    public StatisticsDto getStatistics() {
        LOGGER.debug("Enter recentStatistics method");

        // 文章总数
        Long articles = contentDao.getArticleCount();

        // 评论总数
        Long comments = commentDao.getCommentCount();

        // 链接数
        Long links = metaDao.getMetasCountByType(Types.LINK.getType());

        // 获取附件数
        Long attAches = attAchDao.getAttAchCount();

        StatisticsDto rs = new StatisticsDto();
        rs.setArticles(articles);
        rs.setComments(comments);
        rs.setLinks(links);
        rs.setAttachs(attAches);
        LOGGER.debug("Exit recentStatistics method");
        return rs;
    }
}
