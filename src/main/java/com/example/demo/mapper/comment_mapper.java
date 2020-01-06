package com.example.demo.mapper;


import com.example.demo.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Select("select speaker,content,time,article_id,is_read from comment where article_id=#{article_id}")
    public List<Comment> get_comments(@Param("article_id") int article_id);
}
