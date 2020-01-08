package com.example.demo.mapper;

import com.example.demo.domain.Likes;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */

@Mapper
public interface likes_mapper {

    /**
     * 根据文章id查询文章的点赞信息
     * @param article_id_liked 查询的文章id
     * @return 返回点赞信息
     */
    @Select("select * from likes where article_id_liked=#{article_id_liked}")
    public List<Likes> query_likes_according_to_article_id_mapper(@Param("article_id_liked") int article_id_liked);

    /**
     * 向数据库中插入新的点赞信息
     * @param liker 点赞者
     * @param article_id_liked 被点赞的文章id
     * @param time 点赞时间
     */
    @Insert("insert into likes (liker,article_id_liked,time) values (#{liker},#{article_id_liked},#{time})")
    public void insert_likes_mapper(@Param("liker") String liker,@Param("article_id_liked") int article_id_liked,@Param("time") String time);

    /**
     * 给定点赞的id，删除点赞信息
     * @param likes_id 点赞的id
     */
    @Delete("delete from likes where likes_id=#{likes_id}")
    public void delete_likes_according_to_likes_id_mapper(@Param("likes_id") int likes_id);
}
