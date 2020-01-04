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
    public List<ArticleList> query_article_list(String author){
        return ArticleMapper.query_article_list_mapper(author);
    }

    @Override
    public void insert_data_into_article_list(ArticleList articleList){
        ArticleMapper.insert_data_into_article_list_mapper(articleList);
    }
}
