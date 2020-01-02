package com.example.demo.mapper;

import com.example.demo.domain.ArticleList;
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
    public List<ArticleList> query_article_list(String author);

}
