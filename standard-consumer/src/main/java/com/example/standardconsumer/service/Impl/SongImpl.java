package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.dao.AlbumMapper;
import com.example.standardconsumer.dao.BuyMapper;
import com.example.standardconsumer.dao.SongMapper;
import com.example.standardconsumer.dao.UserMapper;
import com.example.standardconsumer.dao.CommentsMapper;
import com.example.standardconsumer.domain.*;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.feignApi.NewsServiceApi;
import com.example.standardconsumer.feignApi.SongServiceApi;
import com.example.standardconsumer.feignApi.UserServiceApi;
import com.example.standardconsumer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SongImpl implements SongService {

    @Autowired
    SongServiceApi songServiceApi;

    @Autowired
    NewsServiceApi newsServiceApi;

    @Autowired
    UserServiceApi userServiceApi;

    @Override
    public ResultEntity getSingersInSong(String songid){
        ArrayList<Singer> singers = songServiceApi.getSingersInSong(songid);
        if(singers == null)
            return new ResultEntity(false,"查询"+songid+"时出错",null);
        else
            return new ResultEntity(true,"",singers);
    }

    @Override
    public ResultEntity getBoughtSongByUserId(String userid){
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<Buy> buys = songServiceApi.getBoughtSongByUserId(userid);
        for(Buy buy:buys){
            songs.add(songServiceApi.getSongById(buy.getMusicid()));
        }
        return new ResultEntity(true,"",songs);
    }

    @Override
    public Song getSongById(String songid){
        ArrayList<Song> songs = songServiceApi.getSongsById(songid);
        Song song;
        if(songs.size() == 0){
            song = new Song();
            song.setSongid("null");
            song.setSongname("null");
        }
        else song = songs.get(0);
        return song;
    }

    @Override
    public ArrayList<comments> getCommentsInSong(String songid){
        ArrayList<comments> comments = newsServiceApi.getCommentsInSong(songid);
        if(comments == null){
            comments = new ArrayList<>();
        }
        return comments;
    }
    @Override
    public boolean commentSong(String words,String songid,String userid){
        String result = newsServiceApi.commentSong(words,songid,userid);
        if(result.equals("1"))
            return true;
        return false;
    }

    public ArrayList<Song> getSongByNamePart(String name){
        return songServiceApi.getSongByNamePart(name);
    }

    public ArrayList<Song> getCommandSong(String userid){
        return songServiceApi.getCommandSong(userid);
    }

    public boolean playSong(String songid,String userid){
        String succ = songServiceApi.songPlaytimesPlus(userid);
        return succ.equals("1");
    }

}
