package com.example.demo.controller;


import com.example.demo.entity.BuyKey;
import com.example.demo.entity.User;
import com.example.demo.entity.result.ResultEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/database/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUserById")
    ResultEntity getUserById(@RequestParam String userid){
        return userService.getUserById(userid);
    }

    @GetMapping(value = "/isFriendExist")
    String isFriendExist(@RequestParam String userid,@RequestParam String friendid){
        return userService.isFriendExist(userid,friendid);
    }

    @GetMapping(value = "/getUserByNamePart")
    ArrayList<User>  getUserByNamePart(@RequestParam String username){
        return userService.getUserByNamePart(username);
    }

    @PostMapping(value = "/setVIP")
    Map<String,Object> setVIP(@RequestParam String userid){
        return userService.setVIP(userid);
    }


    @PostMapping(value = "/getBoughtSongs")
    ResultEntity getBoughtSongs(@RequestParam String userid){
        return userService.getBoughtSongs(userid);
    }

    @PostMapping(value = "/getBoughtAlbums")
    ResultEntity getBoughtAlbums(@RequestParam String userid){
        return userService.getBoughtAlbums(userid);
    }

    @PostMapping(value = "/isUserBanned")
    Object isUserBanned(@RequestParam String userid){
        return userService.isUserBanned(userid);
    }
}
