package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.domain.News;
import com.example.standardconsumer.domain.NewsComment;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.feignApi.NewsServiceApi;
import com.example.standardconsumer.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsImpl implements NewsService {

    @Autowired
    NewsServiceApi newsServiceApi;

    @Override
    public ArrayList<News> getAllNews(String userid){
        ArrayList<News> news =newsServiceApi.getAllNews(userid);
        return news;
    }

    @Override
    public boolean likeNews(String uid,String nid){
        boolean msg =newsServiceApi.likeNews(uid,nid);
        return msg;
    }
    @Override
    public boolean dislikeNews(String uid,String nid){
        boolean msg =newsServiceApi.dislikeNews(uid,nid);
        return msg;
    }
    @Override
    public boolean isUserLikeNews(String uid,String nid){
        boolean msg =newsServiceApi.isUserLikeNews(uid,nid);
        return msg;
    }
    @Override
    public String commentNews(String uid,String nid,String text){
        String id =newsServiceApi.commentNews(uid,nid,text);
        return id;
    }

    public String answerComment(String uid,String cid,String text){
        String id =newsServiceApi.commentNews(uid,cid,text);
        return id;
    }

    public ArrayList<User> getNewsLike(String nid){
        ArrayList<User>likers = newsServiceApi.getNewsLike(nid);
        return likers;
    }

    public ArrayList<User> getNewsForward(String nid){
        ArrayList<User>likers = newsServiceApi.getNewsForward(nid);
        return likers;
    }

    public ArrayList<NewsComment> getNewsComment(String nid){
        ArrayList<NewsComment>comments = newsServiceApi.getNewsComment(nid);
        return comments;
    }

    public String createNews(String uid,String type,String contentid,String text){
        String id =newsServiceApi.createNews(uid,type,contentid,text);
        return id;
    }

    public boolean deleteNews(String nid){
        boolean msg =newsServiceApi.deleteNews(nid);
        return msg;
    }

    public boolean deleteNewsComment(String cid){
        boolean msg =newsServiceApi.deleteNews(cid);
        return msg;
    }

    public boolean forwardNews(String uid,String nid,String text){
        boolean msg =newsServiceApi.forwardNews(uid,nid,text);
        return msg;
    }

    public boolean likeComment(String uid,String cid){
        boolean msg =newsServiceApi.likeComment(uid,cid);
        return msg;
    }
    public boolean dislikeComment(String uid,String cid){
        boolean msg =newsServiceApi.dislikeComment(uid,cid);
        return msg;
    }
    public boolean  isUserLikeComment(String uid,String cid){
        boolean msg =newsServiceApi.isUserLikeComment(uid,cid);
        return msg;
    }
    public int getCommentLikeNum(String cid){
        int num =newsServiceApi.getCommentLikeNum(cid);
        return num;
    }
}
