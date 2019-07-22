package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.Buy;
import com.example.standardconsumer.domain.History;
import com.example.standardconsumer.domain.Singer;
import com.example.standardconsumer.domain.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient("database-providr")
@RequestMapping("/database/song")
public interface SongServiceApi
{
    @GetMapping(value = "/getSongBySingerId")
    ArrayList<Song> getSongBySingerId(@RequestParam String singerID);

    @PostMapping(value = "/addSinger")
    Integer addSinger(@RequestBody Singer singer);

    @PostMapping(value = "/updateHistory")
    Integer updateHistory(@RequestParam String userID, @RequestParam String singerID);

    @GetMapping(value = "/getSingersInSong")
    ArrayList<Singer> getSingersInSong(@RequestParam String songid);

    @GetMapping(value = "/getBoughtSongByUserId")
    ArrayList<Buy> getBoughtSongByUserId(@RequestParam String userid);

    @GetMapping(value = "/getSongById")
    Song getSongById(@RequestParam String songid);

    @GetMapping(value = "/getSongsById")
    ArrayList<Song> getSongsById(@RequestParam String songid);

    @GetMapping(value = "/getSongByNamePart")
    ArrayList<Song> getSongByNamePart(@RequestParam String name);

    @GetMapping(value = "/getCommandSong")
    ArrayList<Song> getCommandSong(@RequestParam String userid);

    @GetMapping(value = "/songPlaytimesPlus")
    String songPlaytimesPlus(@RequestParam String userid);

}
