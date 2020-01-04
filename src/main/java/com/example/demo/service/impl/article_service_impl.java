package com.example.demo.service.impl;

import com.example.demo.domain.ArticleList;
import com.example.demo.mapper.article_mapper;
import com.example.demo.service.article_service;
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
    public List<String> query_username_according_to_privilege(int privilege){
        return ArticleMapper.query_username_according_to_privilege_mapper(privilege);
    }

    @Override
    public ArticleList query_article_according_to_article_id(int article_id){
        return ArticleMapper.query_article_according_to_article_id_mapper(article_id);
    }
}
