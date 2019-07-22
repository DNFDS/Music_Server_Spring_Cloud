package com.example.standardconsumer.service;

import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Singer;
import com.example.standardconsumer.domain.Song;

import java.util.ArrayList;

public interface SingerService {
    Singer getSingerById(String singerid);
    ArrayList<Singer> getSingerUserLike(String userid);
    int getFansNum(String singerid);
    boolean isUserLikeSinger(String singerid,String userid);
    boolean followSinger(String userid,String singerid);
    boolean unfollowSinger(String userid,String singerid);
    ArrayList<Song> getSingerSong(String singerid);
    ArrayList<Album> getSingerAlbum(String singerid);
    ArrayList<Singer> getSingerByNamePart(String word);
}
