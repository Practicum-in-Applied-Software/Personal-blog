package com.example.demo.domain;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */
public class Likes {

    private int likes_id;

    private String liker;

    private int article_id_liked;

    private String time;

    //为了方便前端显示界面增设的一个字段，数据库中并没有这个字段
    private String article_title;

    //为了方便前端显示界面增设的一个字段，数据库中并没有这个字段
    private String article_author;

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_title() {
        return article_title;
    }

    public Likes(int likes_id, String liker, int article_id_liked, String time) {
        this.likes_id = likes_id;
        this.liker = liker;
        this.article_id_liked = article_id_liked;
        this.time = time;
        article_title = null;
        article_author = null;
    }

    public int getLikes_id() {
        return likes_id;
    }

    public String getLiker() {
        return liker;
    }

    public int getArticle_id_liked() {
        return article_id_liked;
    }

    public void setLikes_id(int likes_id) {
        this.likes_id = likes_id;
    }

    public void setLiker(String liker) {
        this.liker = liker;
    }

    public void setArticle_id_liked(int article_id_liked) {
        this.article_id_liked = article_id_liked;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
