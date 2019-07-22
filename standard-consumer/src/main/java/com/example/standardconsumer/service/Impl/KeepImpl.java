package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.feignApi.KeepServiceApi;
import com.example.standardconsumer.service.KeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class KeepImpl implements KeepService {

    @Autowired
    KeepServiceApi keepServiceApi;

    public String KeepSong(String songid,String songlistid){
        String msg=keepServiceApi.KeepSong(songid,songlistid);
        return msg;
    }
    public String unKeepSongList(String songlistid,String userid){
        String msg=keepServiceApi.unKeepSongList(songlistid,userid);
        return msg;
    }
    public String KeepSongList(String songlistid,String userid){
        String msg=keepServiceApi.KeepSongList(songlistid,userid);
        return msg;
    }
    public String unKeepSong(String songid,String songlistid){
        String msg=keepServiceApi.unKeepSong(songid,songlistid);
        return msg;
    }
}
