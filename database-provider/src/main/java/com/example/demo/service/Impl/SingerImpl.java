package com.example.demo.service.Impl;

import com.example.demo.dao.AlbumMapper;
import com.example.demo.dao.SingerMapper;
import com.example.demo.dao.SongMapper;
import com.example.demo.entity.Album;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.service.SingerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class SingerImpl implements SingerService {
    @Resource
    private SingerMapper singerMapper;
    @Resource
    private SongMapper songMapper;
    @Resource
    AlbumMapper albumMapper;
    public ArrayList<Singer> getSingerById(String singerid){
        Map<String,Object> map = new HashMap<>();
        map.put("singerid",singerid);
        singerMapper.getSingerById(map);
        ArrayList<Singer> singers = ( ArrayList<Singer>)map.get("singers");
        return singers;
    }
    public ArrayList<Song> getSingerSong(String singerid){
        Map<String,Object> map = new HashMap<>();
        map.put("singerid",singerid);
        songMapper.getSongBySingerId(map);
        return (ArrayList<Song>)map.get("songs");

    }

    public ArrayList<Singer> getSingerByNamePart(String word){
        Map<String,Object> map = new HashMap<>();
        map.put("singername",word);
        singerMapper.getSingerByName_part(map);
        return (ArrayList<Singer>)map.get("singers");
    }

    public ArrayList<Album> getSingerAlbum(String singerid){
        Map<String,Object> map = new HashMap<>();
        map.put("singerid",singerid);
        albumMapper.getAlbumBySingerId(map);
        return (ArrayList<Album>)map.get("albums");
    }

    public int getFansNum(String singerid){
        Map<String,Object> map = new HashMap<>();
        map.put("singerid",singerid);
        singerMapper.getSingerFansNum(map);
        return (int)map.get("fansnum");
    }

    public String followSinger(String userid,String singerid){
        Map<String,Object> map = new HashMap<>();
        map.put("singerid",singerid);
        map.put("userid",userid);
        singerMapper.likeSinger(map);
        String succ = (String) map.get("succ");
        return succ;
    }
    public String unfollowSinger(String userid, String singerid){
        Map<String,Object> map = new HashMap<>();
        map.put("singerid",singerid);
        map.put("userid",userid);
        singerMapper.unlikeSinger(map);
        String succ = (String) map.get("succ");
        return succ;
    }
    public String isUserLikeSinger(String singerid, String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("singerid",singerid);
        map.put("userid",userid);
        singerMapper.isUserLikeSinger(map);
        String succ = (String) map.get("islike");
        return succ;
    }
    public ArrayList<Singer> getSingerUserLike(String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        singerMapper.getSingerUserLike(map);
        return (ArrayList<Singer>)map.get("singers");
    }

    public ArrayList<Singer> getSongSinger(String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        singerMapper.getAllSinger(map);
        return (ArrayList<Singer>)map.get("singers");
    }
}
