package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@FeignClient("database-providr")
@RequestMapping("/database/songlist")
public interface SongListServiceApi
{
    @GetMapping(value = "/getSongLists")
    ResultEntity getSongLists(@RequestParam String userid);

    @GetMapping(value = "/getSongListCreatedByUserId")
    ArrayList<SongList> getSongListCreatedByUserId(@RequestParam String userid);

    @GetMapping(value = "/getSongListKeepedByUserId")
    ArrayList<SongList> getSongListKeepedByUserId(@RequestParam String userid);

    @GetMapping(value = "/getSongListById")
    ArrayList<SongList> getSongListById(@RequestParam String songlistid);

    @GetMapping(value = "/getSongsInSongList")
    ArrayList<Song> getSongsInSongList(@RequestParam String songlistid);

    @GetMapping(value = "/getSongListSavedNum")
    int getSongListSavedNum(@RequestParam String songlistid);

    @GetMapping(value = "/getSongListByNamePart")
    ArrayList<SongList> getSongListByNamePart(@RequestParam String name);

    @PostMapping(value = "/createSongList")
    String createSongList(@RequestParam Map<String,Object> map);

    @GetMapping(value = "/getPushSonglist")
    ArrayList<SongList> getPushSonglist(@RequestParam String userid);

    @PostMapping(value = "/deleteSongList")
    String deleteSongList(@RequestParam String songlistid);

    @PostMapping(value = "/updateSongListName")
    String updateSongListName(@RequestParam String name,@RequestParam String songlistid);

    @PostMapping(value = "/deleteSongFromSongList")
    boolean deleteSongFromSongList(@RequestParam String songid,@RequestParam String songlistid);

    @GetMapping(value = "/isSonglistSaved")
    String isSonglistSaved(@RequestParam String userid,@RequestParam String songlistid);
}
