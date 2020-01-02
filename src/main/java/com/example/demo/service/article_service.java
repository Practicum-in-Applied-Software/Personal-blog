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
     * @return 给出作者返回作者的所以文章
     */

    public List<ArticleList> article_list_query(String author);

}
