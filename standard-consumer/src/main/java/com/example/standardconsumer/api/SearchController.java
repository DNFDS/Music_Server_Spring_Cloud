package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.service.AlbumService;
import com.example.standardconsumer.service.SongListService;
import com.example.standardconsumer.service.SongService;
import com.example.standardconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/standard-consumer")
public class SearchController {
    @Autowired
    UserService userService;
    @Autowired
    SongService songService;
    @Autowired
    SongListService songListService;
    @Autowired
    AlbumService albumService;

    @UserLog("SearchController")
    @GetMapping(value ={"/api/Search","/api/Search/Song"})
    public Object search(@RequestParam("words")String searchWords){
        return songService.getSongByNamePart(searchWords);
    }

    @UserLog("SearchController")
    @GetMapping(value ="/api/Search/SongList")
    public Object searchSonglist(@RequestParam("words")String searchWords){
        return songListService.getSongListByNamePart(searchWords);
        
    }

    @UserLog("SearchController")
    @GetMapping(value ="/api/Search/Album")
    public Object searchAlbum(@RequestParam("words")String searchWords){
        return albumService.getAlbumByNamePart(searchWords);
    }

    @UserLog("SearchController")
    @GetMapping(value ="/api/Search/User")
    public Object searchUser(@RequestParam("words")String searchWords){
        return userService.getUserByNamePart(searchWords);
    }



}
