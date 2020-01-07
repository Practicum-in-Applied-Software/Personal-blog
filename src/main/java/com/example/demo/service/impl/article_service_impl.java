package com.example.demo.service.impl;

import com.example.demo.domain.ArticleList;
import com.example.demo.domain.Comment;
import com.example.demo.mapper.article_mapper;
import com.example.demo.service.article_service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */

@Service
public class article_service_impl implements article_service {


    @Autowired
    private article_mapper ArticleMapper;

    @Override
    public List<ArticleList> query_article_according_to_username(String author){
        return ArticleMapper.query_article_according_to_username_mapper(author);
    }

    @Override
    public void insert_data_into_article_list(ArticleList articleList){
        ArticleMapper.insert_data_into_article_list_mapper(articleList);
    }

    @Override
    public void update_article_status(int article_id,boolean visible){
        ArticleMapper.update_article_status_mapper(article_id,visible);
    }

    @Override
    public void delete_article(int article_id){
        ArticleMapper.delete_article_mapper(article_id);
    }

    @Override
    public ArticleList query_article_according_to_article_id(int article_id){
        return ArticleMapper.query_article_according_to_article_id_mapper(article_id);
    }

    @Override
    public void update_article_access_count_according_to_article_id(int article_id, int access_count){
        ArticleMapper.update_article_access_count_according_to_article_id_mapper(article_id,access_count);
    }
    @Override
    public void update_article_according_to_article_id(ArticleList articleList){
        ArticleMapper.update_article_according_to_article_id_mapper(articleList);
    }
}
