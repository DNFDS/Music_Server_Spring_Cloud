package com.example.demo.service;

import com.example.demo.entity.History;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongList;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PlayerService
{
    ArrayList<Song> getSongByID(Map<String, Object> Map);
    ArrayList<Song> getListByAlbumID(Map<String, Object> Map);
    ArrayList<SongList> getListByListID(Map<String, Object> Map);
    Integer addSong(Song song);
    Integer addUserHistory(String userID,String singerID);
    String[] getRecommendSingers(String userID)throws Exception;
    ArrayList<SongList> getSongsInSongList(Map<String, Object> Map);
}
