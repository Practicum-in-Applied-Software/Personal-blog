package com.example.demo.controller;

import com.example.demo.domain.ArticleList;
import com.example.demo.domain.User;
import com.example.demo.service.CookiesService;
import com.example.demo.service.LoginService;
import com.example.demo.service.article_service;
import com.example.demo.service.likes_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */


@Controller
public class Index {

    @Autowired
    private article_service ArticleService;

    @Autowired
    private CookiesService cookiesService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private likes_service likesService;


    @RequestMapping("/index")
    public String index(ModelMap model){

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

        User user=loginService.User_query(username);

        model.addAttribute("user",user);

        int privilege=Integer.valueOf(cookiesService.getCookies("privilege"));

        String prvilege_str=null;

        if(privilege==0){
            prvilege_str="Ordinary user";
        }
        else if(privilege==1){
            prvilege_str="Administrator";
        }
        else{
            prvilege_str="root";
        }
        model.addAttribute("privilege",prvilege_str);

        List<ArticleList> articleListList=ArticleService.query_article_according_to_username(username);
        model.addAttribute("articleListList",articleListList);

        return "demo1/index";
    }

    /**
     * 和数据库中的信息进行对比，对Cookie进行验证，防止伪造Cookie
     * @return 返回username
     */
    public String CookieCheck(){

        String username=cookiesService.getCookies("username");
        String privilege=cookiesService.getCookies("privilege");
        String pwd=cookiesService.getCookies("pwd");

        System.out.println("uername:"+username);
        System.out.println("privilege:"+privilege);
        System.out.println("pwd:"+pwd);

        if(username==null || privilege==null || pwd==null){
            return null;
        }

        //和数据库中信息进行对比
        User user=loginService.User_query(username);
        if(username.equals(user.getName()) && privilege.equals(String.valueOf(user.getPrivilege())) && pwd.equals(user.getPwd())){
            return username;
        }
        return null;
    }

}
