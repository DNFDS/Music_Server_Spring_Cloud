package com.example.demo.userInterface;

import com.example.demo.entity.Singer;
import com.example.demo.entity.User;
import com.example.demo.service.SingerService;
import com.example.demo.service.SongService;
import com.example.demo.util.AutoShowUtil;

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
public class SingerInterface {
    @Autowired
    private AutoShowUtil showUtil;
    @Autowired
    private SingerService singerService;
    @Autowired
    private SongService songService;

    @GetMapping(value ="/api/getSinger")
    public Object getSinger(@Param("singerid") String singerid){
        return singerService.getSingerById(singerid);
    }

    @GetMapping(value = "/api/getSingerBySong")
    public Object getSingerBySong(@Param("songid")String songid){
        return songService.getSingersInSong(songid).getObject();
    }

    @GetMapping(value = "/api/searchSinger")
    public Object searchSinger(@Param("word")String word){
        return singerService.getSingerByNamePart(word);
    }


    @RequestMapping(value = "/profile/showFollowSinger", method = RequestMethod.GET)
    public ModelAndView showFollowSinger(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession(false).getAttribute("visted");
        User my =(User) request.getSession(false).getAttribute("user");
        ArrayList<Singer> Follows = (ArrayList<Singer>)singerService.getSingerUserLike(user.getUserid());
        Map<String,Object> map  = showUtil.showSingerFollow(my.getUserid(),Follows);
        return new ModelAndView("temp/follows/follow_singer",map);
    }

    @RequestMapping(value = "/profile/changeFollowSinger", method = RequestMethod.GET)
    public Map<String, String> changeFollowSinger(HttpServletRequest request){
        User my =(User) request.getSession(false).getAttribute("user");
        String id = request.getParameter("id");
        return showUtil.changeFollowSinger(my.getUserid(),id);
    }

    @GetMapping(value="/api/getSingerAlbums")
    public Object getSingerAlbums(@Param("singerid") String singerid){
        return singerService.getSingerAlbum(singerid);
    }
}
