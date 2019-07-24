package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.entity.result.ResultEntity;
import com.example.demo.service.*;
import com.example.demo.util.AutoShowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/database/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping(value = "/getCommandAlbum")
    public ArrayList<Album> getCommandAlbum(@RequestParam String userid){
        return albumService.getCommandAlbum(userid);
    }

    @GetMapping(value = "/getAlbumByAlbumId")
    public ArrayList<Album> getAlbumByAlbumId(@RequestParam String albumid){
        return albumService.getAlbumByAlbumId(albumid);
    }

    @GetMapping(value = "/getAlbumByNamePart")
    public ArrayList<Album> getAlbumByNamePart(@RequestParam String name){
        return albumService.getAlbumByNamePart(name);
    }

    @GetMapping(value = "/getSongsInAlbum")
    public ArrayList<Song> getSongsInAlbum(@RequestParam String albumid){
        return albumService.getSongsInAlbum(albumid);
    }

    @GetMapping(value = "/getAlbumBySingerId")
    public ArrayList<Album> getAlbumBySingerId(@RequestParam String singerid){
        return albumService.getAlbumBySingerId(singerid);
    }
}
