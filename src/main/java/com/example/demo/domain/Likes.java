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

    public Likes(int likes_id, String liker, int article_id_liked, String time) {
        this.likes_id = likes_id;
        this.liker = liker;
        this.article_id_liked = article_id_liked;
        this.time = time;
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
