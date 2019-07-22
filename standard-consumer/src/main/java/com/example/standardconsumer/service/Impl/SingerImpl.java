package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.dao.AlbumMapper;
import com.example.standardconsumer.dao.SingerMapper;
import com.example.standardconsumer.dao.SongMapper;
import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Singer;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.feignApi.*;
import com.example.standardconsumer.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class SingerImpl implements SingerService {
    @Autowired
    SingerServiceApi singerServiceApi;

    @Autowired
    AlbumServiceApi albumServiceApi;

    @Autowired
    SongServiceApi songServiceApi;

    public Singer getSingerById(String singerid){
        ArrayList<Singer> singers = singerServiceApi.getSingerById(singerid);;
        Singer singer;
        if(singers.size() == 0){
            singer = new Singer();
        }
        else {
            singer = singers.get(0);
        }
        return singer;
    }
    public ArrayList<Song> getSingerSong(String singerid){
        return songServiceApi.getSongBySingerId(singerid);

    }

    public ArrayList<Singer> getSingerByNamePart(String word){
        return singerServiceApi.getSingerById(word);
    }

    public ArrayList<Album> getSingerAlbum(String singerid){
        return albumServiceApi.getAlbumBySingerId(singerid);
    }

    public int getFansNum(String singerid){
        return singerServiceApi.getFansNum(singerid);
    }

    public boolean followSinger(String userid,String singerid){
        String succ = singerServiceApi.likeSinger(userid,singerid);
        if(succ.equals("1")){
            return true;
        }
        return false;
    }
    public boolean unfollowSinger(String userid,String singerid){
        String succ = singerServiceApi.unlikeSinger(userid,singerid);
        if(succ.equals("1")){
            return true;
        }
        return false;
    }
    public boolean isUserLikeSinger(String singerid,String userid){
        String succ = singerServiceApi.isUserLikeSinger(singerid,userid);
        if(succ.equals("1")){
            return true;
        }
        return false;
    }
    public ArrayList<Singer> getSingerUserLike(String userid){
        ArrayList<Singer> singers = singerServiceApi.getSingerUserLike(userid);
        return singers;
    }

}
