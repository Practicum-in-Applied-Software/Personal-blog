package com.example.demo.mapper;

import com.example.demo.domain.ArticleList;
import com.example.demo.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */

@Mapper
public interface article_mapper {

    /**
     *
     * @param author 作者
     * @return 在数据库中查询作者的文章
     */

    @Select("select article_id,author,title,tags,content,createTime,updateTime,access_count,visible from article where author=#{author}")
    public List<ArticleList> query_article_according_to_username_mapper(String author);


    /**
     * 在数据库中插入文章数据
     * @param articleList 需要插入的数据
     */
    @Insert("insert into article (author,title,tags,content,createTime,updateTime,access_count,visible) values (#{author},#{title},#{tags},#{content},#{createTime},#{updateTime},#{access_count},#{visible})")
    public void insert_data_into_article_list_mapper(ArticleList articleList);

    /**
     * 把文章id为article_id的文章的状态更新为visible状态
     * @param article_id 文章id
     * @param visible 更新后的文章状态
     */
    @Update("update article set visible=#{visible} where article_id=#{article_id}")
    public void update_article_status_mapper(@Param("article_id") int article_id,@Param("visible") boolean visible);

    /**
     * 删除文章id为article_id的文章
     * @param article_id 需要删除的文章的id
     */
    @Delete("delete from article where article_id=#{article_id}")
    public void delete_article_mapper(int article_id);

    /**
     * 查询权限为privilege的所有用户的用户名
     * @param prvilege 需要查询的权限
     * @return 返回用户名
     */
    @Select("select username from user where privilege=#{prvilege}")
    public List<String> query_username_according_to_privilege_mapper(int prvilege);


    /**
     *
     * 查询文章id为article_id的信息
     * @param article_id 文章的id
     * @return 返回文章信息
     */
    @Select("select * from article where article_id=#{article_id}")
    public ArticleList query_article_according_to_article_id_mapper(int article_id);


    /**
     * 将id为article_id的文章访问次数更新为access_count
     * @param article_id 需要更新的文章的id
     * @param access_count 更新后的access_count
     */
    @Update("update article set access_count=#{access_count} where article_id=#{article_id}")
    public void update_article_access_count_according_to_article_id_mapper(@Param("article_id")int article_id,@Param("access_count") int access_count);

    /**
     * 按照id更新article
     * @param articleList 被更新之后的article
     */
    @Update("update article set title=#{title},tags=#{tags},content=#{content},updateTime=#{updateTime},visible=#{visible} where article_id=#{article_id}")
    public void update_article_according_to_article_id_mapper(ArticleList articleList);

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
    @Insert("insert into comment (speaker,article_id,content,time) values (#{speaker},#{article_id},#{content},#{time})")
    public void insert_comment(@Param("speaker") String speaker,@Param("article_id")int article_id,@Param("content") String content,@Param("time") String time);
    /**
     * 获取指定article_id下的评论
     * @param article_id 评论的文章的id
     */
    @Select("select * from comment where article_id=#{article_id}")
    public List<Comment> get_comments(@Param("article_id") int article_id);


}
