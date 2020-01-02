package com.example.demo.domain;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */
public class ArticleList {

    private int article_id;

    private String author;

    private String title;

    private String tags;

    private String content;

    private String craeteTime;

    private String upadteTime;

    private int access_count;

    private boolean visible;

    public int getArticle_id() {
        return article_id;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public String getContent() {
        return content;
    }

    public String getCraeteTime() {
        return craeteTime;
    }

    public String getUpadteTime() {
        return upadteTime;
    }

    public int getAccess_count() {
        return access_count;
    }

    public ArticleList(int article_id, String author, String title, String tags, String content, String craeteTime, String upadteTime, int access_count, boolean visible) {
        this.article_id = article_id;
        this.author = author;
        this.title = title;
        this.tags = tags;
        this.content = content;
        this.craeteTime = craeteTime;
        this.upadteTime = upadteTime;
        this.access_count = access_count;
        this.visible = visible;
    }

    public String getAuthor() {
        return author;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCraeteTime(String craeteTime) {
        this.craeteTime = craeteTime;
    }

    public void setUpadteTime(String upadteTime) {
        this.upadteTime = upadteTime;
    }

    public void setAccess_count(int access_count) {
        this.access_count = access_count;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
