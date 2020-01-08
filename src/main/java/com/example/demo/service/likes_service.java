package com.example.demo.service;

import com.example.demo.domain.Likes;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */
public interface likes_service {

    /**
     * 根据文章id查询文章的点赞信息
     * @param article_id_liked 查询的文章id
     * @return 返回点赞信息
     */
    public List<Likes> query_likes_according_to_article_id(int article_id_liked);

    /**
     * 向数据库中插入新的点赞信息
     * @param liker 点赞者
     * @param article_id_liked 被点赞的文章id
     * @param time 点赞时间
     */
    public void insert_likes(String liker,int article_id_liked,String time);

    /**
     * 给定点赞的id，删除点赞信息
     * @param likes_id 点赞的id
     */
    public void delete_likes_according_to_likes_id(int likes_id);
}
