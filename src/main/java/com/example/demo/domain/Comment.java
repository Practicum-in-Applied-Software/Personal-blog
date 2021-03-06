package com.example.demo.domain;

public class Comment {
    private String speaker;
    private String content;
    private String time;

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    private int article_id;
    private int id;

    public boolean isIs_read() {
        return is_read;
    }

    private boolean is_read;

    //新加字段article_title，文章标题，只是方便为了给前端返回数据
    private String article_title;

    //article_author，文章标题，只是方便为了给前端返回数据
    private String article_author;

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_title() {
        return article_title;
    }

    public Comment(int id, String speaker, String content, String time, int article_id,boolean is_read) {
        this.id = id;
        this.speaker = speaker;
        this.content = content;
        this.time = time;
        this.article_id = article_id;
        this.is_read = is_read;
        article_title = null;
        article_author = null;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
