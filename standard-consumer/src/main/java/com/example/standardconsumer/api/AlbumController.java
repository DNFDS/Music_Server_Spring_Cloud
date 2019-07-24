package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.standardconsumer.service.AlbumService;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/standard-consumer")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @UserLog("AlbumController")
    @RequestMapping(value ="/api/getAlbum",method = RequestMethod.GET)
    public Object getAlbum(@Param("albumid") String albumid){
        return albumService.getAlbumByAlbumId(albumid);
    }

    @UserLog("AlbumController")
    @GetMapping(value="/api/getSongsInAlbum")
    public ArrayList<Song> getSongsInAlbum(@Param("albumId") String albumId){
        return albumService.getSongsInAlbum(albumId);
    }
}