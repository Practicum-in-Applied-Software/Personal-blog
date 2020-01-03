package com.example.demo.mapper;

import com.example.demo.domain.ArticleList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    public List<ArticleList> query_article_list_mapper(String author);


    /**
     * 在数据库中插入文章数据
     * @param articleList 需要插入的数据
     */
    @Insert("insert into article (author,title,tags,content,createTime,updateTime,access_count,visible) values (#{author},#{title},#{tags},#{content},#{createTime},#{updateTime},#{access_count},#{visible})")
    public void insert_data_into_article_list_mapper(ArticleList articleList);

}
