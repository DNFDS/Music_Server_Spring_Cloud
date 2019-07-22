package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.News;
import com.example.standardconsumer.domain.NewsComment;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.comments;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@FeignClient("database-providr")
@RequestMapping("/database/news")
public interface NewsServiceApi {

    @GetMapping(value = "/getAllNews")
    ArrayList<News> getAllNews(@RequestParam  String userid);

    @GetMapping(value = "/likeNews")
    boolean likeNews(@RequestParam String uid,@RequestParam String nid);

    @GetMapping(value = "/dislikeNews")
    boolean dislikeNews(@RequestParam String uid,@RequestParam String nid);

    @GetMapping(value = "/likeComment")
    boolean likeComment(@RequestParam String uid, @RequestParam String cid);

    @GetMapping(value = "/dislikeComment")
    boolean dislikeComment(@RequestParam String uid,@RequestParam String cid);

    @GetMapping(value = "/isUserLikeComment")
    boolean isUserLikeComment(@RequestParam String uid, @RequestParam String cid);

    @GetMapping(value = "/isUserLikeNews")
    boolean isUserLikeNews(@RequestParam String uid,@RequestParam String nid);

    @GetMapping(value = "/commentNews")
    String commentNews(@RequestParam String uid, @RequestParam String nid,@RequestParam  String text);

    @GetMapping(value = "/answerComment")
    String answerComment(@RequestParam String uid, @RequestParam String cid,@RequestParam String text);

    @GetMapping(value = "/getNewsLike")
    ArrayList<User> getNewsLike(@RequestParam String nid);

    @GetMapping(value = "/getCommentLikeNum")
    int getCommentLikeNum(@RequestParam String cid);

    @GetMapping(value = "/getNewsForward")
    ArrayList<User> getNewsForward(@RequestParam String nid);

    @GetMapping(value = "/getNewsComment")
    ArrayList<NewsComment> getNewsComment(@RequestParam String nid);

    @GetMapping(value = "/createNews")
    String createNews(@RequestParam String uid, @RequestParam String type, @RequestParam String contentid, @RequestParam String text);

    @GetMapping(value = "/deleteNews")
    boolean deleteNews(@RequestParam String nid);

    @GetMapping(value = "/deleteNewsComment")
    boolean deleteNewsComment(@RequestParam String cid);

    @GetMapping(value = "/forwardNews")
    boolean forwardNews(@RequestParam String uid, @RequestParam String nid, @RequestParam String text);

    @GetMapping(value = "/getCommentsInSong")
    ArrayList<comments> getCommentsInSong(@RequestParam  String songid);

    @PostMapping(value = "/commentSong")
    String commentSong(@RequestParam String words,@RequestParam String songid,@RequestParam String userid);
}
