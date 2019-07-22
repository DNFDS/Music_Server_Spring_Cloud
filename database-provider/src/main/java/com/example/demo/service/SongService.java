package com.example.demo.service;

import com.example.demo.entity.Buy;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.comments;

import java.util.ArrayList;

public interface SongService {
    boolean playSong(String songid,String userid);
    ArrayList<Song> getCommandSong(String userid);
    ArrayList<Song> getSongByNamePart(String name);
    ArrayList<Singer> getSingersInSong(String songid);
    ArrayList<comments> getCommentsInSong(String songid);
    ArrayList<Buy> getBoughtSongByUserId(String userid);
    Song getSongById(String songid);
    boolean commentSong(String words,String songid,String userid);
	String isSongBought(String songid, String albumid, String userid);
}
