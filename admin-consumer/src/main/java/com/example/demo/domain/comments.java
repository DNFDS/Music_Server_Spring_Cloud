package com.example.demo.domain;

public class comments extends commentsKey {
    private String commenttext;

    private String read;

    public String getCommenttext() {
        return commenttext;
    }
    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext == null ? null : commenttext.trim();
    }

    public String getRead(){
        return read;
    }

    public void setRead(String read){
        this.read=read;
    }
}