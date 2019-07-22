package com.example.demo.controller;


import com.example.demo.entity.News;
import com.example.demo.entity.NewsComment;
import com.example.demo.entity.User;
import com.example.demo.entity.comments;
import com.example.demo.service.KeepService;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/database/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/getAllNews")
    ArrayList<News> getAllNews(@RequestParam  String userid){
        return newsService.getAllNews(userid);
    }

    @GetMapping(value = "/likeNews")
    boolean likeNews(@RequestParam String uid,@RequestParam String nid){
        return newsService.likeNews(uid,nid);
    }

    @GetMapping(value = "/dislikeNews")
    boolean dislikeNews(@RequestParam String uid,@RequestParam String nid){
        return newsService.dislikeNews(uid,nid);
    }

    @GetMapping(value = "/likeComment")
    boolean likeComment(@RequestParam String uid, @RequestParam String cid){
        return newsService.likeComment(uid,cid);
    }

    @GetMapping(value = "/dislikeComment")
    boolean dislikeComment(@RequestParam String uid,@RequestParam String cid){
        return newsService.dislikeComment(uid,cid);
    }

    @GetMapping(value = "/isUserLikeComment")
    boolean isUserLikeComment(@RequestParam String uid, @RequestParam String cid){
        return newsService.isUserLikeComment(uid,cid);
    }

    @GetMapping(value = "/isUserLikeNews")
    boolean isUserLikeNews(@RequestParam String uid,@RequestParam String nid){
        return newsService.isUserLikeNews(uid,nid);
    }

    @GetMapping(value = "/commentNews")
    String commentNews(@RequestParam String uid, @RequestParam String nid,@RequestParam  String text){
        return newsService.commentNews(uid,nid,text);
    }

    @GetMapping(value = "/answerComment")
    String answerComment(@RequestParam String uid, @RequestParam String cid,@RequestParam String text){
        return newsService.commentNews(uid,cid,text);
    }

    @GetMapping(value = "/getNewsLike")
    ArrayList<User> getNewsLike(@RequestParam String nid){
        return newsService.getNewsLike(nid);
    }

    @GetMapping(value = "/getCommentLikeNum")
    int getCommentLikeNum(@RequestParam String cid){
        return newsService.getCommentLikeNum(cid);
    }

    @GetMapping(value = "/getNewsForward")
    ArrayList<User> getNewsForward(@RequestParam String nid){
        return newsService.getNewsForward(nid);
    }

    @GetMapping(value = "/getNewsComment")
    ArrayList<NewsComment> getNewsComment(@RequestParam String nid){
        return newsService.getNewsComment(nid);
    }

    @GetMapping(value = "/createNews")
    String createNews(@RequestParam String uid, @RequestParam String type, @RequestParam String contentid, @RequestParam String text){
        return newsService.createNews(uid,type,contentid,text);
    }

    @GetMapping(value = "/deleteNews")
    boolean deleteNews(@RequestParam String nid){
        return newsService.deleteNews(nid);
    }

    @GetMapping(value = "/deleteNewsComment")
    boolean deleteNewsComment(@RequestParam String cid){
        return newsService.deleteNewsComment(cid);
    }

    @GetMapping(value = "/forwardNews")
    boolean forwardNews(@RequestParam String uid, @RequestParam String nid, @RequestParam String text){
        return newsService.forwardNews(uid,nid,text);
    }

    @GetMapping(value = "/getCommentsInSong")
    ArrayList<comments> getCommentsInSong(@RequestParam  String songid){
        return newsService.getCommentsInSong(songid);
    }

    @PostMapping(value = "/commentSong")
    String commentSong(@RequestParam String words,@RequestParam String songid,@RequestParam String userid){
        return newsService.commentSong(words,songid,userid);
    }
}
