package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.service.AlbumService;
import com.example.standardconsumer.service.SongService;
import com.example.standardconsumer.service.UserService;
import com.example.standardconsumer.util.AutoShowUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/standard-consumer")
public class BuyController {
    @Autowired
    private SongService songService;
    @Autowired
    private UserService userService;
    @Autowired
    private AutoShowUtil showUtil;

    @UserLog("BuyController")
    @RequestMapping(value = "/profile/showMyBought", method = RequestMethod.GET)
    public ModelAndView showMyBught(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession(false).getAttribute("visted");
        ResultEntity e = songService.getBoughtSongByUserId(user.getUserid());
        ArrayList<SongList> boughtSongList = (ArrayList<SongList>)e.getObject();
        Map<String,Object> map = showUtil.showSongList(boughtSongList);
        return new ModelAndView("temp/mybought_main",map);
    }

    @UserLog("BuyController")
    @GetMapping(value = "/api/showBoughtSongs")
    public Object showBoughtSongs(@Param("id") String id){
        return userService.getBoughtSongs(id).getObject();
    }

    @UserLog("BuyController")
    @GetMapping(value = "/api/showBoughtAlbums")
    public Object showBoughtAlbums(@Param("id") String id){
        return userService.getBoughtAlbums(id).getObject();
    }

}