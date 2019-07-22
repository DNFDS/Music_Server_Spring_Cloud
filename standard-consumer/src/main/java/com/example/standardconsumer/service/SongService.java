package com.example.standardconsumer.service;

import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.comments;
import com.example.standardconsumer.domain.result.ResultEntity;

import java.util.ArrayList;

public interface SongService {
    boolean playSong(String songid,String userid);
    ArrayList<Song> getCommandSong(String userid);
    ArrayList<Song> getSongByNamePart(String name);
    ResultEntity getSingersInSong(String songid);
    ArrayList<comments> getCommentsInSong(String songid);
    ResultEntity getBoughtSongByUserId(String userid);
    Song getSongById(String songid);
    boolean commentSong(String words,String songid,String userid);
}
