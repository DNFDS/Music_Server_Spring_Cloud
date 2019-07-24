package com.example.demo.controller;


import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongList;
import com.example.demo.entity.result.ResultEntity;
import com.example.demo.service.SingerService;
import com.example.demo.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/database/songlist")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @GetMapping(value = "/getSongListById")
    ArrayList<SongList> getSongListById(@RequestParam String songlistid){
        return songListService.getSongListById(songlistid);
    }

    @GetMapping(value = "/getSongsInSongList")
    ArrayList<Song> getSongsInSongList(@RequestParam String songlistid){
        return songListService.getSongsInSongList(songlistid);
    }

    @GetMapping(value = "/getSongListSavedNum")
    int getSongListSavedNum(@RequestParam String songlistid){
        return songListService.getSongListSavedNum(songlistid);
    }

    @GetMapping(value = "/getSongListByNamePart")
    ArrayList<SongList> getSongListByNamePart(@RequestParam String name){
        return songListService.getSongListByNamePart(name);
    }

    @PostMapping(value = "/deleteSongList")
    String deleteSongList(@RequestParam String songlistid){
        return songListService.deleteSongList(songlistid);
    }

    @PostMapping(value = "/updateSongListName")
    String updateSongListName(@RequestParam String name,@RequestParam String songlistid){
        return songListService.changeSongListName(name,songlistid);
    }

    @PostMapping(value = "/deleteSongFromSongList")
    boolean deleteSongFromSongList(@RequestParam String songid,@RequestParam String songlistid){
        return songListService.deleteSongInList(songid,songlistid);
    }

    @GetMapping(value = "/isSonglistSaved")
    String isSonglistSaved(@RequestParam String userid,@RequestParam String songlistid){
        return songListService.isSonglistSaved(userid,songlistid);
    }
}
