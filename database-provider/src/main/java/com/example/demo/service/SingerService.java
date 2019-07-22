package com.example.demo.service;

import com.example.demo.entity.Album;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;

import java.util.ArrayList;
import java.util.List;

public interface SingerService {
    ArrayList<Singer> getSingerById(String singerid);
    ArrayList<Singer> getSingerUserLike(String userid);
    int getFansNum(String singerid);
    String isUserLikeSinger(String singerid, String userid);
    String followSinger(String userid,String singerid);
    String unfollowSinger(String userid, String singerid);
    ArrayList<Song> getSingerSong(String singerid);
    ArrayList<Album> getSingerAlbum(String singerid);
    ArrayList<Singer> getSingerByNamePart(String word);
    ArrayList<Singer> getSongSinger(String userid);
    List<Object> getSingerRankList();
}
