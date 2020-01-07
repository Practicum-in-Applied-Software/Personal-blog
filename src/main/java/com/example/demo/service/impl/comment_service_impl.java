package com.example.demo.service.impl;

import com.example.demo.domain.Comment;
import com.example.demo.mapper.comment_mapper;
import com.example.demo.service.comment_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class comment_service_impl implements comment_service {

    @Autowired
    private comment_mapper CommentMapper;

    //    评论功能
    @Override
    public void insert_comment(String speaker,int article_id,String content,String time){
        CommentMapper.insert_comment(speaker,article_id,content,time);
    }
    //    获取文章对应的所有评论
    @Override
    public List<Comment> get_comments(int article_id) {
        return CommentMapper.get_comments(article_id);
    }

    @Override
    public void update_comment_status(int id,boolean is_read){
        CommentMapper.update_comment_status_mapper(id,is_read);
    }
}
