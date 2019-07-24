package com.example.demo.controller;


import com.example.demo.entity.History;
import com.example.demo.service.KeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/database/keep")
public class KeepController {

    @Autowired
    private KeepService keepService;

    @GetMapping(value = "/KeepSong")
    public String KeepSong(@RequestParam String songid,@RequestParam String songlistid){
        return keepService.KeepSong(songid,songlistid);
    }

    @GetMapping(value = "/unKeepSongList")
    public String unKeepSongList(@RequestParam String songlistid,@RequestParam String userid){
        return keepService.unKeepSongList(songlistid,userid);
    }

    @GetMapping(value = "/KeepSongList")
    public String KeepSongList(String songlistid,String userid){
        return keepService.KeepSongList(songlistid,userid);
    }

    @GetMapping(value = "/unKeepSong")
    public String unKeepSong(String songid,String songlistid){
        return keepService.unKeepSong(songid,songlistid);
    }
}
