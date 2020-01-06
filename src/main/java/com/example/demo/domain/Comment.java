package com.example.demo.domain;

public class Comment {
    private String speaker;
    private String content;
    private String time;
    private int article_id;

    public Comment(String speaker, String content, String time, int article_id) {
        this.speaker = speaker;
        this.content = content;
        this.time = time;
        this.article_id = article_id;
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
}
