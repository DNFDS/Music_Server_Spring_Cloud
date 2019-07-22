package com.example.standardconsumer.api;


import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.service.SingerService;
import com.example.standardconsumer.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private SingerService singerService;

    // @RequestMapping(value = "/profile/like_song", method = RequestMethod.GET)
    // public String mainPage(HttpServletRequest request,Map<String, Object> map) {
    //     ResultEntity e;
    //     User user = (User) request.getSession(false).getAttribute("visted");
    //     e = userService.getFans(user);
    //     ArrayList<User> fans = (ArrayList<User>)e.getObject();
    //     e= userService.getFriends(user);
    //     ArrayList<User> friends = (ArrayList<User>)e.getObject();
    //     ArrayList<Singer> singers = singerService.getSingerUserLike(user.getUserid());
    //     map.put("FansNum",fans.size());
    //     map.put("FriendsNum",friends.size()+singers.size());
    //     return "profile/like_song";
    // }
    
}
