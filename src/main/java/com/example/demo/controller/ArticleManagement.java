package com.example.demo.controller;

import com.example.demo.domain.ArticleList;
import com.example.demo.service.article_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */

@Controller
public class ArticleManagement {

    @Autowired
    private article_service ArticleService;


    @RequestMapping("/bloglist")
    public String BlogList(ModelMap modelMap){

        List<ArticleList> articleLists=DataCut(ArticleService.article_list_query("admin"));

        modelMap.addAttribute("articleLists",articleLists);

        return "index/BlogList";
    }

    @ResponseBody
    @RequestMapping("/bloglist/data")
    public List<ArticleList> BlogListData(){

        List<ArticleList> articleLists=DataCut(ArticleService.article_list_query("admin"));

        return articleLists;
    }


    /**
     * 当用户的文章标题、内容过长时只在表格里面显示一部分
     * @param articleLists 用户的文章列表
     * @return 用户的文章列表
     */

    public List<ArticleList> DataCut(List<ArticleList> articleLists){

        for(ArticleList item:articleLists){

            if(item.getTitle().length()>16){
                item.setTitle(item.getTitle().substring(0,16));
            }

            if(item.getAuthor().length()>12){
                item.setAuthor(item.getAuthor().substring(0,12));
            }

            if(item.getTags().length()>12){
                item.setTags(item.getTags().substring(0,12));
            }

            if(item.getContent().length()>19){
                item.setContent(item.getContent().substring(0,20));
            }

            item.setCraeteTime(item.getCraeteTime().substring(0,10));

            item.setUpadteTime(item.getUpadteTime().substring(0,10));
        }
        return articleLists;
    }
}
