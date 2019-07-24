package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.Singer;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.service.SingerService;
import com.example.standardconsumer.service.SongService;
import com.example.standardconsumer.util.AutoShowUtil;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/standard-consumer")
public class SingerController {
    @Autowired
    private AutoShowUtil showUtil;
    @Autowired
    private SingerService singerService;
    @Autowired
    private SongService songService;

    @UserLog("SingerController")
    @GetMapping(value ="/api/getSinger")
    public Object getSinger(@Param("singerid") String singerid){
        return singerService.getSingerById(singerid);
    }

    @UserLog("SingerController")
    @GetMapping(value = "/api/getSingerBySong")
    public Object getSingerBySong(@Param("songid")String songid){
        return songService.getSingersInSong(songid).getObject();
    }

    @UserLog("SingerController")
    @GetMapping(value = "/api/searchSinger")
    public Object searchSinger(@Param("word")String word){
        return singerService.getSingerByNamePart(word);
    }

    @UserLog("SingerController")
    @GetMapping(value = "/profile/showFollowSinger")
    public ModelAndView showFollowSinger(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession(false).getAttribute("visted");
        User my =(User) request.getSession(false).getAttribute("user");
        ArrayList<Singer> Follows = (ArrayList<Singer>)singerService.getSingerUserLike(user.getUserid());
        Map<String,Object> map  = showUtil.showSingerFollow(my.getUserid(),Follows);
        return new ModelAndView("temp/follows/follow_singer",map);
    }

    @UserLog("SingerController")
    @GetMapping(value = "/profile/changeFollowSinger")
    public Map<String, String> changeFollowSinger(HttpServletRequest request){
        User my =(User) request.getSession(false).getAttribute("user");
        String id = request.getParameter("id");
        return showUtil.changeFollowSinger(my.getUserid(),id);
    }

    @UserLog("SingerController")
    @GetMapping(value="/api/getSingerAlbums")
    public Object getSingerAlbums(@Param("singerid") String singerid){
        return singerService.getSingerAlbum(singerid);
    }
}