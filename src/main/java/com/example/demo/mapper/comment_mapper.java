package com.example.demo.mapper;


import com.example.demo.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 蔡秉岐
 * @Class: 计科1604
 * @Number: 2016014393
 */

@Mapper
public interface comment_mapper {
    /**
     * 插入用户评论
     * @param article_id 评论的文章的id
     * @param content 评论内容
     * @param time 评论时间
     * @param speaker 评论者
     */
    @Insert("insert into comment (speaker,article_id,content,time) values (#{speaker},#{article_id},#{content},#{time})")
    public void insert_comment(@Param("speaker") String speaker, @Param("article_id")int article_id, @Param("content") String content, @Param("time") String time);
    /**
     * 获取指定article_id下的评论
     * @param article_id 评论的文章的id
     */
    @Select("select id,speaker,content,time,article_id,is_read from comment where article_id=#{article_id}")
    public List<Comment> get_comments(@Param("article_id") int article_id);

    /**
     * 把特定的文章的评论状态置为is_read
     * @param id 评论的id
     * @param is_read 评论的状态
     */
    @Update("update comment set is_read=#{is_read} where id=#{id}")
    public void update_comment_status_mapper(@Param("id") int id,@Param("is_read") boolean is_read);
}
