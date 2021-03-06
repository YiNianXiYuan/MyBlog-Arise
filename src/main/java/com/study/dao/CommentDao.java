package com.study.dao;

import com.study.dto.cond.CommentCond;
import com.study.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:51
 * @Description: 评论相关Dao接口
 */
@Mapper
public interface CommentDao {
    /**
     * 添加评论
     * @param comments
     */
    void addComment(Comment comments);

    /**
     * 根据文章ID获取评论
     * @param cid
     * @return
     */
    List<Comment> getCommentByCId(@Param("cid") Integer cid);


    /**
     * 删除评论
     * @param coid
     */
    void deleteComment(@Param("coid") Integer coid);

    /**
     * 获取评论总数
     * @return
     */
    Long getCommentCount();

    /**
     * 根据条件获取评论列表
     * @param commentCond
     * @return
     */
    List<Comment> getCommentsByCond(CommentCond commentCond);

    /**
     * 通过ID获取评论
     * @param coid
     * @return
     */
    Comment getCommentById(@Param("coid") Integer coid);

    /**
     * 更新评论状态
     * @param coid
     * @param status
     */
    void updateCommentStatus(@Param("coid") Integer coid, @Param("status") String status);
}
