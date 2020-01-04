package com.example.demo.service;

import com.example.demo.domain.ArticleList;

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

    public List<ArticleList> query_article_list(String author);


    /**
     * 在数据库中插入文章数据
     * @param articleList 需要插入的数据
     */
    public void insert_data_into_article_list(ArticleList articleList);
}
