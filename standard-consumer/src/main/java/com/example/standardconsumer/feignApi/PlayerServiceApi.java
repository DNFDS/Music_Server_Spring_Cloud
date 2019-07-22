package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@FeignClient("database-providr")
@RequestMapping("/database/player")
public interface PlayerServiceApi
{
    @GetMapping(value = "/getSongByID")
    ArrayList<Song> getSongByID(@RequestParam Map<String, Object> Map);

    @GetMapping(value = "/getListByAlbumID")
    ArrayList<Song> getListByAlbumID(@RequestParam Map<String, Object> Map);

    @GetMapping(value = "/getListByListID")
    ArrayList<SongList> getListByListID(@RequestParam Map<String, Object> Map);

    @GetMapping(value = "/getSongsInSongList")
    ArrayList<SongList> getSongsInSongList(@RequestParam Map<String, Object> Map);

    @PostMapping(value = "/addSong")
    Integer addSong(@RequestBody Song song);

    @PostMapping(value = "/addUserHistory")
    Integer addUserHistory(@RequestParam String userID, @RequestParam String singerID);

    @GetMapping(value = "/getRecommendSingers")
    String[] getRecommendSingers(@RequestParam String userID)throws Exception;
}
