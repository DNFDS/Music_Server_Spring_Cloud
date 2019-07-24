package com.example.demo.controller;


import com.example.demo.entity.Buy;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongList;
import com.example.demo.service.SongListService;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/database/song")
public class SongController {

    @Autowired
    private SongService songService;


    @GetMapping(value = "/getSingersInSong")
    ArrayList<Singer> getSingersInSong(@RequestParam String songid){
        return songService.getSingersInSong(songid);
    }

    @GetMapping(value = "/getBoughtSongByUserId")
    ArrayList<Buy> getBoughtSongByUserId(@RequestParam String userid){
        return songService.getBoughtSongByUserId(userid);
    }

    @GetMapping(value = "/getSongById")
    Song getSongById(@RequestParam String songid){
        return songService.getSongById(songid);
    }

    @GetMapping(value = "/getSongByNamePart")
    ArrayList<Song> getSongByNamePart(@RequestParam String name){
        return songService.getSongByNamePart(name);
    }

    @GetMapping(value = "/getCommandSong")
    ArrayList<Song> getCommandSong(@RequestParam String userid){
        return songService.getCommandSong(userid);
    }

}
