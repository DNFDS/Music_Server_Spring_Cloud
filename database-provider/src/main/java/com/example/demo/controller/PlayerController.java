package com.example.demo.controller;

import com.example.demo.entity.Song;
import com.example.demo.entity.SongList;
import com.example.demo.service.PlayerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Controller
@CrossOrigin
@RequestMapping("/database/player")
public class PlayerController
{
    @Autowired
    private PlayerService playerService;
    @GetMapping(value = "/getSongByID")
    ArrayList<Song> getSongByID(@RequestParam Map<String, Object> Map){
        return playerService.getSongByID(Map);
    }

    @GetMapping(value = "/getListByAlbumID")
    ArrayList<Song> getListByAlbumID(@RequestParam Map<String, Object> Map){
        return playerService.getListByAlbumID(Map);
    }

    @GetMapping(value = "/getListByListID")
    ArrayList<SongList> getListByListID(@RequestParam Map<String, Object> Map){
        return playerService.getListByListID(Map);
    }

    @GetMapping(value = "/getSongsInSongList")
    ArrayList<SongList> getSongsInSongList(@RequestParam Map<String, Object> Map){
        return playerService.getSongsInSongList(Map);
    }

    @PostMapping(value = "/addSong")
    Integer addSong(@RequestBody Song song){
        return playerService.addSong(song);
    }

    @PostMapping(value = "/addUserHistory")
    Integer addUserHistory(@RequestParam String userID, @RequestParam String singerID){
        return playerService.addUserHistory(userID,singerID);
    }

    @GetMapping(value = "/getRecommendSingers")
    String[] getRecommendSingers(@RequestParam String userID)throws Exception{
        return playerService.getRecommendSingers(userID);
    }
}
