package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@FeignClient("database-providr")
@RequestMapping("/database/keep")
public interface KeepServiceApi {

    @GetMapping(value = "/KeepSong")
    public String KeepSong(@RequestParam String songid,@RequestParam String songlistid);

    @GetMapping(value = "/unKeepSongList")
    public String unKeepSongList(@RequestParam String songlistid,@RequestParam String userid);

    @GetMapping(value = "/KeepSongList")
    public String KeepSongList(String songlistid,String userid);

    @GetMapping(value = "/unKeepSong")
    public String unKeepSong(String songid,String songlistid);
}
