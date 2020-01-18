package com.example.demo.controller;

import com.example.demo.domain.ArticleList;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Likes;
import com.example.demo.domain.User;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

import java.util.*;

import java.util.ArrayList;
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

    @Autowired
    private CookiesService cookiesService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private comment_service CommentService;

    @Autowired
    private ChangePersonalInfoService changePersonalInfoService;

    @Autowired
    private likes_service LikesService;

    /***
     * 博客列表页面
     * @param model 页面上要显示的元素
     * @return 返回博客列表页面
     */
    @RequestMapping("/bloglist")
    public String BlogList(ModelMap model){

        String username=CookieCheck();
        System.out.println(username);

        if(username==null){
            return "redirect:/error_page";
        }

        User user=loginService.User_query(username);

        String privilege=cookiesService.getCookies("privilege");

        List<ArticleList> articleLists=ArticleService.query_article_according_to_username(username);

        if(privilege.equals("1")||privilege.equals("2")){
            articleLists=ArticleService.query_article_according_to_username(username);
            List<String> alluser=loginService.query_username_according_to_privilege(0);
            for(String item:alluser){
                List<ArticleList> tmp=ArticleService.query_article_according_to_username(item);
                for(ArticleList article:tmp){
                    articleLists.add(article);
                }
            }
        }

        if(privilege.equals("2")){
            List<String> alluser=loginService.query_username_according_to_privilege(1);
            for(String item:alluser){
                List<ArticleList> tmp=ArticleService.query_article_according_to_username(item);
                for(ArticleList article:tmp){
                    articleLists.add(article);
                }
            }
        }

        model.addAttribute("user",user);

        int privilege_num=Integer.valueOf(privilege);

        if(privilege_num==0){
            privilege="Ordinary user";
        }
        else if(privilege_num==1){
            privilege="Administrator";
        }
        else{
            privilege="root";
        }

        model.addAttribute("privilege",privilege);
        articleLists=DataCut(articleLists);
        model.addAttribute("articleLists",articleLists);
        return "index/BlogList";
    }

    @ResponseBody
    @RequestMapping("/bloglist/data")
    public List<ArticleList> BlogListData(){

        List<ArticleList> articleLists=DataCut(ArticleService.query_article_according_to_username("admin"));//admin为暂时的用户

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
    public String AddArticle(ModelMap model){
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

        return "index/AddArticle";
    }

    /**
     * 用于添加文章到数据库
     * @param article_name 文章标题（不能为空）
     * @param article_tags 文章标签（可以为空）
     * @param content 文章内容（可以为空）
     * @param article_status 文章状态，可见或不可见（公开或私有）
     * @return 返回主页
     */
    @RequestMapping(value = "/addarticle_to_database",method = RequestMethod.POST)
    public String AddArticleToDatabase(@RequestParam("article_name") String article_name,@RequestParam("article_tags") String article_tags,@RequestParam("editor-markdown-doc") String content,@RequestParam("article_status") String article_status){

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

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

        ArticleList articleList=new ArticleList(0,username,article_name,article_tags,content,currentTime,currentTime,0,visible);

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

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

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

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

        ArticleService.delete_article(article_id);
        return "redirect:/bloglist";
    }

    @RequestMapping("/article/access_limit")
    public String access_limit(){
        return "index/access_limit";
    }

    @RequestMapping("/article/view/{article_id}")
    public String article_view(@PathVariable("article_id") int article_id,ModelMap model){

        ArticleList article=ArticleService.query_article_according_to_article_id(article_id);

        if(article==null){
            return "redirect:/article/access_limit";
        }

        String username=CookieCheck();

        if(!article.isVisible()){

            if(username==null){
                return "redirect:/article/access_limit";
            }

            int privilege=Integer.valueOf(cookiesService.getCookies("privilege"));

            User author=loginService.User_query(article.getAuthor());

            //如果文章不可见，且文章作者的privilege大于等于查看者的privilege，则不允许查看
            if(!username.equals(author.getName()) && privilege<=author.getPrivilege()){
                return "redirect:/article/access_limit";
            }
        }

        List<Likes> likesList=LikesService.query_likes_according_to_article_id(article_id);

        model.addAttribute("likes_number",likesList.size());

        boolean is_liked=false;

        for(Likes item:likesList){
            if(item.getLiker().equals(username)){
                is_liked=true;

                model.addAttribute("likes_id",item.getLikes_id());
                break;
            }
        }

        model.addAttribute("is_liked",is_liked);
        article.setAccess_count(article.getAccess_count()+1);
        ArticleService.update_article_access_count_according_to_article_id(article.getArticle_id(),article.getAccess_count());
        model.addAttribute("article",article);
        List<Comment> comments=CommentService.get_comments(article_id);

        System.out.println(comments);
        System.out.println(article.getArticle_id());
        System.out.println(article.getAccess_count());

        model.addAttribute("comments",comments);
        return "index/articleview";
    }

    @RequestMapping("/article/edit/{article_id}")
    public String article_edit(@PathVariable("article_id") int article_id,ModelMap model){
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

        ArticleList articleList=ArticleService.query_article_according_to_article_id(article_id);
        if(articleList==null){
            return "redirect:/error_page";
        }
        model.addAttribute("article",articleList);
        return "index/EditArticle";
    }

    @RequestMapping(value = "/edit_article",method = RequestMethod.POST)
    public String edit_article(@RequestParam("article_id") String article_id,@RequestParam("article_name") String article_name,@RequestParam("article_tags") String article_tags,@RequestParam("editor-markdown-doc") String content,@RequestParam("article_status") String article_status){
        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

        String currentTime=CurrentTime();

        System.out.println(article_name);
        System.out.println(article_tags);
        System.out.println(content);
        System.out.println(article_status);

        boolean visible=false;

        if(article_status.equals("visible")){
            visible=true;
        }

        System.out.println(visible);
        ArticleList articleList=new ArticleList(Integer.valueOf(article_id),username,article_name,article_tags,content,null,currentTime,0,visible);
        ArticleService.update_article_according_to_article_id(articleList);

        String ans="redirect:/article/view/"+article_id;
        System.out.println(ans);
        return ans;
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
    /**
     * 获取评论区内容
     */
//    @ResponseBody
    @RequestMapping(value = "/get_comments",method = RequestMethod.POST)
    public String get_comments(@RequestParam("article_id")String article_id){
        System.out.println("ok");
        System.out.println(article_id);

        return "skjafl";
    }

    /**
     * 获取发表评论区内容并加到数据库
     */
    @RequestMapping(value = "/upload_comment",method = RequestMethod.POST)
    public String upload_article(@RequestParam("article_id") String article_id,@RequestParam("comment") String comment){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = simpleDateFormat.format(date);

            String speaker=CookieCheck();

            if(speaker==null){
                return "redirect:/error_page";
            }

            int trans_article_id=Integer.parseInt(article_id);

            CommentService.insert_comment(speaker,trans_article_id,comment,time,false);
            String url="/article/view/"+article_id;
            return "redirect:"+url;

    }

    @RequestMapping("/information/{username}")
    public String information(@PathVariable("username") String username,ModelMap model){

        User user=loginService.User_query(username);
        if(user==null){
            return "redirect:/error_page";
        }

        if(user.getEmail()==null){
            user.setEmail("未知");
        }
        if(user.getPhone()==null){
            user.setPhone("未知");
        }
        if(user.getSex()==null){
            user.setSex("未知");
        }

        model.addAttribute("user",user);
        return "aboutme/aboutme";
    }


    @RequestMapping("/comment_view/{user_viewed}")
    public String comment_view(@PathVariable("user_viewed") String user_viewed,ModelMap model){
        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

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

        User user=loginService.User_query(username);
        model.addAttribute("user",user);

        boolean view_self=false;

        System.out.println(user_viewed);

        List<Comment> commentList=new ArrayList<>();
        List<ArticleList> articleLists=null;


        if(username.equals(user_viewed)){
            view_self=true;
            articleLists=ArticleService.query_article_according_to_username(user_viewed);
        }
        //如果不是查看自己的文章评论，则查看权限比自己低一级的所有用户的文章评论
        else{
            if(!user_viewed.equals("all")){
                return "redirect:/error_page";
            }
            if(privilege<=0){
                return "redirect:/error_page";
            }

            articleLists=new ArrayList<>();
            for(int i=0;i<privilege;i++){
                List<String> authors=loginService.query_username_according_to_privilege(i);

                for(String tmp:authors){
                    System.out.println(tmp);
                }

                for(String item:authors){
                    List<ArticleList> tmp=ArticleService.query_article_according_to_username(item);
                    articleLists.addAll(tmp);
                }
            }
        }

        for(ArticleList article:articleLists){
            List<Comment> commentList_tmp=CommentService.get_comments(article.getArticle_id());

            for(Comment item:commentList_tmp){
                item.setArticle_title(article.getTitle());
                item.setArticle_author(article.getAuthor());
                commentList.add(item);
            }
        }

        commentList=comment_cut(commentList);
        model.addAttribute("view_self",view_self);
        model.addAttribute("comments",commentList);

        return "index/comment_view";
    }

    //对comment进行处理，过长则用省略号代替
    public List<Comment> comment_cut(List<Comment> comments){

        for(Comment comment:comments){

            if(comment.getArticle_author().length()>10){
                comment.setArticle_author(comment.getArticle_author().substring(0,10)+"...");
            }

            if(comment.getArticle_title().length()>15){
                comment.setArticle_title(comment.getArticle_title().substring(0,15)+"...");
            }

            if(comment.getSpeaker().length()>12){
                comment.setSpeaker(comment.getSpeaker().substring(0,12)+"...");
            }

            if(comment.getContent().length()>16){
                comment.setContent(comment.getContent().substring(0,16)+"...");
            }

        }

        return comments;
    }

    @RequestMapping(value = "/flip_comment_status",method = RequestMethod.POST)
    public String flip_comment_status(@RequestParam("comment_id") String comment_id,@RequestParam("view_self") boolean view_self,@RequestParam("is_read") boolean is_read){

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

        CommentService.update_comment_status(Integer.valueOf(comment_id),!is_read);

        System.out.println("comment_id:"+comment_id);

        System.out.println("view_self:"+view_self);

        String ret=null;

        if(view_self){
            ret="redirect:/comment_view/"+username;
        }
        else{
            ret="redirect:/comment_view/all";
        }

        return ret;
    }
//    修改个人信息界面
    @RequestMapping(value="/edit_personal_info")
    public String edit_personal_info(ModelMap model){
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

        return "demo1/edit_personal_info";
    }
    @RequestMapping(value="/change_personal_info",method = RequestMethod.POST)
    public String change_personal_info( @RequestParam("password") String pwd, @RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("sex") String sex,ModelMap model) {
        User user = loginService.User_query(CookieCheck());
        String old_password = user.getPwd();
        String old_email = user.getEmail();
        String old_sex = user.getSex();
        String old_phone = user.getPhone();
        System.out.println("++++++++++++++++++++");
        System.out.println(old_sex);
        System.out.println(old_phone);
        System.out.println(old_email);
        System.out.println(old_password);
        System.out.println("++++++++++++++++++++");
        System.out.println(pwd);
        System.out.println(phone);
        System.out.println(email);
        System.out.println(sex);
        Map<String, String> map = new HashMap<String, String>();
        if (pwd.length() == 0) {
            pwd = old_password;
        }
        //        简单邮箱格式验证
        if (email.length() == 0) {
            email = old_email;
        } else if (!email.contains("@")) {
            map.put("num", "1");
            System.out.println("1");
            return "redirect:/edit_personal_info?1";
        }
        //        性别验证
        if (sex.length() == 0)
            sex = old_sex;
        if ((!sex.equals("boy") && !sex.equals("girl"))) {
            map.put("num", "2");
            System.out.println("2");
            return "redirect:/edit_personal_info?2";
        }
        //        电话号码格式验证
        if (phone.length() == 0) {
            phone = old_phone;
        } else if (phone.length() != 11) {
            map.put("num", "3");
            System.out.println("3");
            return "redirect:/edit_personal_info?3";
        }
        changePersonalInfoService.change_user_info(pwd, email, sex, phone, CookieCheck());
        return "/login_register/index";
    }
    @RequestMapping(value = "/give_likes",method = RequestMethod.POST)
    public String give_likes(@RequestParam("article_id") int article_id){

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

        System.out.println("likes id is "+article_id);

        LikesService.insert_likes(username,article_id,CurrentTime());

        return "redirect:/article/view/"+article_id;
    }

    @RequestMapping(value = "/give_up_likes",method = RequestMethod.POST)
    public String give_up_likes(@RequestParam("article_id") int article_id,@RequestParam("likes_id") int likes_id){

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }
        LikesService.delete_likes_according_to_likes_id(likes_id);

        return "redirect:/article/view/"+article_id;
    }

    @RequestMapping("/likes_view/{user_viewed}")
    public String likes_view(@PathVariable("user_viewed") String user_viewed,ModelMap model){

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

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
        User user=loginService.User_query(username);
        model.addAttribute("user",user);

        boolean view_self=false;

        List<ArticleList> articleLists=null;

        if(username.equals(user_viewed)){

            System.out.println("okok1");
            view_self=true;
            articleLists=ArticleService.query_article_according_to_username(username);
        }
        else{
            if(!user_viewed.equals("all")){
                return "redirect:/error_page";
            }

            articleLists=new ArrayList<>();
            for(int i=0;i<privilege;i++){

                List<String> names=loginService.query_username_according_to_privilege(i);

                for(String item:names){
                    System.out.print(item+", ");
                }
                System.out.println();

                for(String item:names){
                    List<ArticleList> tmp=ArticleService.query_article_according_to_username(item);
                    articleLists.addAll(tmp);
                }
            }
        }


        List<Likes> likesList=new ArrayList<>();

        for(ArticleList article:articleLists){

            System.out.println(article.getArticle_id());
            System.out.println(article.getTitle());
            System.out.println(article.getAuthor());

            List<Likes> likes=LikesService.query_likes_according_to_article_id(article.getArticle_id());

            if(likes==null)
                continue;

            for(Likes item:likes){
                item.setArticle_title(article.getTitle());
                item.setArticle_author(article.getAuthor());
            }
            likesList.addAll(likes);
        }

        likesList=likes_cut(likesList);

        model.addAttribute("view_self",view_self);
        model.addAttribute("likesList",likesList);

        return "index/likes_view";
    }

    //对likes进行处理，过长则用省略号代替
    public List<Likes> likes_cut(List<Likes> likesList){

        for(Likes item:likesList){

            if(item.getArticle_author().length()>15){
                item.setArticle_author(item.getArticle_author().substring(0,15)+"...");
            }

            if(item.getArticle_title().length()>18){
                item.setArticle_title(item.getArticle_title().substring(0,18)+"...");
            }

            if(item.getLiker().length()>15){
                item.setLiker(item.getLiker().substring(0,15)+"...");
            }

        }
        return likesList;
    }

    @RequestMapping("/{author}/article_list")
    public String author_article(@PathVariable("author") String author,ModelMap model){

        List<ArticleList> tmp=ArticleService.query_article_according_to_username(author);

        String username=CookieCheck();

        boolean f=true;

        //未登录则不允许查看私有文章
        if(username==null){
            f=false;
        }
        else if(!username.equals(author)){

            //要求查看者的权限大于文章作者的权限，否则不允许查看私有文章
            int privilege_author=loginService.User_query(author).getPrivilege();

            if(Integer.valueOf(cookiesService.getCookies("privilege"))<=privilege_author){
                f=false;
            }
        }

        List<ArticleList> articleLists=new ArrayList<>();

        if(f){
            articleLists.addAll(tmp);
        }
        else{
            for(ArticleList item:tmp){
                if(item.isVisible()){
                    articleLists.add(item);
                }
            }
        }

        model.addAttribute("author",author);

        if(articleLists.size()>0){
            model.addAttribute("first_article",articleLists.get(0));
            articleLists.remove(0);
        }

        model.addAttribute("articleLists",articleLists);

        for(ArticleList article:articleLists){
            System.out.println(article.getTitle());
        }

        return "index/article_list";
    }

    @RequestMapping("/blogstatistics/{user_viewed}")
    public String BlogStatistics(@PathVariable("user_viewed") String user_viewed,ModelMap model){

        String username=CookieCheck();

        if(username==null){
            return "redirect:/error_page";
        }

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
        User user=loginService.User_query(username);
        model.addAttribute("user",user);

        boolean view_self=false;

        List<ArticleList> articleLists=null;

        if(user_viewed.equals(username)){
            view_self=true;
            articleLists=ArticleService.query_article_according_to_username(username);
        }
        else{

            articleLists=new ArrayList<>();

            if(!user_viewed.equals("all")){
                return "redirect:/error_page";
            }

            for(int i=0;i<privilege;i++){
                List<String> names=loginService.query_username_according_to_privilege(i);
                for(String item:names){
                    List<ArticleList> tmp=ArticleService.query_article_according_to_username(item);

                    articleLists.addAll(tmp);

                }
            }
        }

        articleLists=DataCut(articleLists);

        model.addAttribute("view_self",view_self);
        model.addAttribute("articleLists",articleLists);
        return "index/BlogStatistics";
    }
}