package com.example.demo.controller;

import com.example.demo.domain.ArticleList;
import com.example.demo.service.article_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
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

        List<ArticleList> articleLists=DataCut(ArticleService.query_article_list("admin"));//admin为暂时的用户

        modelMap.addAttribute("articleLists",articleLists);

        return "index/BlogList";
    }

    @ResponseBody
    @RequestMapping("/bloglist/data")
    public List<ArticleList> BlogListData(){

        List<ArticleList> articleLists=DataCut(ArticleService.query_article_list("admin"));//admin为暂时的用户

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
                item.setTitle(item.getTitle().substring(0,16)+"...");
            }

            if(item.getAuthor().length()>12){
                item.setAuthor(item.getAuthor().substring(0,12)+"...");
            }

            if(item.getTags().length()>12){
                item.setTags(item.getTags().substring(0,12)+"...");
            }

            if(item.getContent().length()>19){
                item.setContent(item.getContent().substring(0,20)+"...");
            }

            item.setCreateTime(item.getCreateTime().substring(0,10));

            item.setUpdateTime(item.getUpdateTime().substring(0,10));
        }
        return articleLists;
    }

    @RequestMapping("/addarticle")
    public String AddArticle(){


        return "index/AddArticle";
    }


    @RequestMapping(value = "/addarticle_to_database",method = RequestMethod.POST)
    public String AddArticleToDatabase(@RequestParam("article_name") String article_name,@RequestParam("article_tags") String article_tags,@RequestParam("editor-markdown-doc") String content,@RequestParam("article_status") String article_status){

        String currentTime=CurrentTime();

        boolean visible=false;

        if(article_status.equals("visible")){
            visible=true;
        }

        System.out.println(article_name);
        System.out.println(article_tags);
        System.out.println(content);
        System.out.println(article_status);
        System.out.println(visible);

        ArticleList articleList=new ArticleList(0,"admin",article_name,article_tags,content,currentTime,currentTime,0,visible);

        ArticleService.insert_data_into_article_list(articleList);
        return "redirect:/index";
    }


    /**
     * 用于返回系统当前时间
     * @return 系统当前时间
     */
    public String CurrentTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
