package com.example.demo.service;

import com.example.demo.domain.ArticleList;
import com.example.demo.domain.Comment;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */
public interface article_service {

    /**
     *
     * @param author 作者
     * @return 给出作者返回作者的所有的文章
     */

    public List<ArticleList> query_article_according_to_username(String author);


    /**
     * 在数据库中插入文章数据
     * @param articleList 需要插入的数据
     */
    public void insert_data_into_article_list(ArticleList articleList);

    /**
     * 把文章id为article_id的文章的状态更新为visible状态
     * @param article_id 文章id
     * @param visible 更新后的文章状态
     */
    public void update_article_status(int article_id,boolean visible);

    /**
     * 删除文章id为article_id的文章
     * @param article_id 需要删除的文章的id
     */
    public void delete_article(int article_id);

    /**
     * 查询权限为privilege的所有用户的用户名
     * @param prvilege 需要查询的权限
     * @return 返回用户名
     */
    public List<String> query_username_according_to_privilege(int prvilege);

    /**
     *
     * 查询文章id为article_id的信息
     * @param article_id 文章的id
     * @return 返回文章信息
     */
    public ArticleList query_article_according_to_article_id(int article_id);

    /**
     * 将id为article_id的文章访问次数更新为access_count
     * @param article_id 需要更新的文章的id
     * @param access_count 更新后的access_count
     */
    public void update_article_access_count_according_to_article_id(int article_id,int access_count);
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
}
