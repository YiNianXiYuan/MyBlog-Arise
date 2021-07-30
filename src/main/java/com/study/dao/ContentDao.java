package com.study.dao;

import com.study.dto.cond.ContentCond;
import com.study.pojo.Content;
import com.study.pojo.RelationShip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:54
 * @Description: 文章相关Dao接口
 */
@Mapper
public interface ContentDao {
    /**
     * 添加文章
     * @param contentDomain
     */
    void addArticle(Content contentDomain);

    /**
     * 根据编号获取文章
     * @param cid
     * @return
     */
    Content getArticleById(Integer cid);

    /**
     * 更新文章
     * @param contentDomain
     */
    void updateArticleById(Content contentDomain);

    /**
     * 根据条件获取文章列表
     * @param contentCond
     * @return
     */
    List<Content> getArticleByCond(ContentCond contentCond);

    /**
     * 删除文章
     * @param cid
     */
    void deleteArticleById(Integer cid);

    /**
     * 获取文章总数
     * @return
     */
    Long getArticleCount();

    /**
     * 通过分类名获取文章
     * @param category
     * @return
     */
    List<Content> getArticleByCategory(@Param("category") String category);

    /**
     * 通过标签获取文章
     * @param cid
     * @return
     */
    List<Content> getArticleByTags(List<RelationShip> cid);
}
