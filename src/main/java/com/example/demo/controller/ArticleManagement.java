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

    /***
     * 博客列表页面，用户暂定为admin
     * @param modelMap 页面上要显示的元素
     * @return 返回博客列表页面
     */
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

    /**
     * 用于添加文章到数据库，用户暂定为admin
     * @param article_name 文章标题（不能为空）
     * @param article_tags 文章标签（可以为空）
     * @param content 文章内容（可以为空）
     * @param article_status 文章状态，可见或不可见（公开或私有）
     * @return 返回主页
     */
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


    /**
     * 用于改变文章状态，例如将公开改为私有，将私有改为公开
     * @param article_id 文章id
     * @param article_status 文章当前状态
     * @return 返回博客列表页面
     */
    @RequestMapping(value = "/flip_visible",method = RequestMethod.POST)
    public String flip_visible(@RequestParam("article_id") int article_id,@RequestParam("article_status") boolean article_status){
        article_status=!article_status;
        ArticleService.update_article_status(article_id,article_status);
        return "redirect:/bloglist";
    }

    /**
     * 用于删除指定id的文章
     * @param article_id 文章id
     * @return 返回博客列表页面
     */
    @RequestMapping(value = "/delete_article",method = RequestMethod.POST)
    public String delete_article(@RequestParam("article_id") int article_id){
        ArticleService.delete_article(article_id);
        return "redirect:/bloglist";
    }
}
