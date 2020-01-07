package com.example.demo.service.impl;

import com.example.demo.domain.Likes;
import com.example.demo.mapper.likes_mapper;
import com.example.demo.service.likes_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */

@Service
public class likes_service_impl implements likes_service {

    @Autowired
    private likes_mapper LikesMapper;

    @Override
    public List<Likes> query_likes_according_to_article_id(int article_id_liked){
        return LikesMapper.query_likes_according_to_article_id_mapper(article_id_liked);
    }

    @Override
    public void insert_likes(String liker,int article_id_liked,String time){
        LikesMapper.insert_likes_mapper(liker,article_id_liked,time);
    }

    @Override
    public void delete_likes_according_to_likes_id(int likes_id){
        LikesMapper.delete_likes_according_to_likes_id_mapper(likes_id);
    }
}
