package com.example.demo.userInterface;

import com.example.demo.entity.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AlbumService;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class AlbumInterface {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value ="/api/getAlbum",method = RequestMethod.GET)
    public Object getAlbum(@Param("albumid") String albumid){
        return albumService.getAlbumByAlbumId(albumid);
    }
    @GetMapping(value="/api/getSongsInAlbum")
    public ArrayList<Song> getSongsInAlbum(@Param("albumId") String albumId){
        return albumService.getSongsInAlbum(albumId);
    }
}
