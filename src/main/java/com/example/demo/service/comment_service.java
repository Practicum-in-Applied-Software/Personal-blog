package com.example.demo.service;

import com.example.demo.domain.Comment;

import java.util.List;

public interface comment_service {
    /**
     * @Author: 蔡秉岐
     * @Class: 计科1604
     * @Number: 2016014393
     */
    /**
     * 插入用户评论
     * @param article_id 评论的文章的id
     * @param content 评论内容
     * @param time 评论时间
     * @param speaker 评论者
     */
    public void insert_comment(String speaker,int article_id,String content,String time);
    /**
     * 获取指定article_id下的评论
     * @param article_id 评论的文章的id
     */
    public List<Comment> get_comments(int article_id);

    /**
     * 把特定的文章的评论状态置为is_read
     * @param id 评论的id
     * @param is_read 评论的状态
     */
    public void update_comment_status(int id,boolean is_read);
}
