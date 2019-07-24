package com.example.demo.controller;


import com.example.demo.entity.Album;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.comments;
import com.example.demo.service.AdminService;
import com.example.demo.service.AlbumService;
import com.example.demo.service.SingerService;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/database/rank")
public class RankController {

    @Autowired
    private SongService songService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private AlbumService albumService;

    @GetMapping(value = "/getSongRankList")
    List<Object> getSongRankList() {
        return songService.getSongRankList();
    }

    @GetMapping(value = "/getSingerRankList")
    List<Object> getSingerRankList() {
        return singerService.getSingerRankList();
    }

    @GetMapping(value = "/getAlbumRankList")
    List<Object> getAlbumRankList() {
        return albumService.getAlbumRankList();
    }
}
