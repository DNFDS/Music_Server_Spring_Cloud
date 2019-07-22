package com.example.demo.controller;


import com.example.demo.entity.Album;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.service.AlbumService;
import com.example.demo.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/database/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @GetMapping(value = "/getSingerById")
    ArrayList<Singer> getSingerById(@RequestParam String singerID){
        return singerService.getSingerById(singerID);
    }

    @GetMapping(value = "/getSingerByName_part")
    ArrayList<Singer> getSingerByNamePart( @RequestParam String word){
        return singerService.getSingerByNamePart(word);
    }

    @GetMapping(value = "/getFansNum")
    int getFansNum( @RequestParam String singerid){
        return singerService.getFansNum(singerid);
    }

    @GetMapping(value = "/likeSinger")
    String likeSinger(@RequestParam String userid,@RequestParam String singerid){
        return singerService.followSinger(userid,singerid);
    }

    @GetMapping(value = "/likeSinger")
    String unlikeSinger(@RequestParam String userid,@RequestParam String singerid){
        return singerService.unfollowSinger(userid,singerid);
    }

    @GetMapping(value = "/isUserLikeSinger")
    String isUserLikeSinger(@RequestParam String singerid,@RequestParam String userid){
        return singerService.isUserLikeSinger(singerid,userid);
    }

    @GetMapping(value = "/getSongSinger")
    ArrayList<Singer> getSongSinger( @RequestParam String userid){
        return singerService.getSongSinger(userid);
    }
}
