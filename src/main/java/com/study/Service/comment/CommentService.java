package com.study.Service.comment;

import com.github.pagehelper.PageInfo;
import com.study.dto.cond.CommentCond;
import com.study.pojo.Comment;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:12
 * @Description: 评论相关Service接口
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comments
     */
    void addComment(Comment comments);

    /**
     * 通过文章ID获取评论
     * @param cid
     * @return
     */
    List<Comment> getCommentsByCId(Integer cid);

    /**
     * 根据条件获取评论列表
     * @param commentCond   查询条件
     * @param pageNum       分页参数 第几页
     * @param pageSize      分页参数 每页条数
     * @return
     */
    PageInfo<Comment> getCommentsByCond(CommentCond commentCond, int pageNum, int pageSize);

    /**
     * 通过ID获取评论
     * @param coid
     * @return
     */
    Comment getCommentById(Integer coid);

    /**
     * 更新评论状态
     * @param coid
     * @param status
     */
    void updateCommentStatus(Integer coid, String status);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(Integer id);
}
