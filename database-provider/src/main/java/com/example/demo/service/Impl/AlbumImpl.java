package com.example.demo.service.Impl;

import com.example.demo.dao.AlbumMapper;
import com.example.demo.dao.SongMapper;
import com.example.demo.entity.Album;
import com.example.demo.entity.Song;
import com.example.demo.service.AlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumImpl implements AlbumService {
    @Resource
    private AlbumMapper albumMapper;
    @Resource
    private SongMapper songMapper;
    @Override
    public ArrayList<Album> getAlbumByAlbumId(String albumid){
        Map<String,Object> map = new HashMap<>();
        map.put("albumid",albumid);
        albumMapper.getAlbumById(map);
        ArrayList<Album> albums = (ArrayList<Album>)map.get("albums");
        return albums;
    }
    @Override
    public ArrayList<Song> getSongsInAlbum(String albumid){
        Map<String,Object> map = new HashMap<>();
        map.put("albumid",albumid);
        songMapper.getSongByAlbumId(map);
        return (ArrayList<Song>)map.get("songs");
    }
    @Override
    public ArrayList<Album> getAlbumByNamePart(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("albumname",name);
        albumMapper.getAlbumByNamePart(map);
        return (ArrayList<Album>)map.get("albums");
    }
    @Override
    public ArrayList<Album> getCommandAlbum(String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        albumMapper.getAllAlbum(map);
        return (ArrayList<Album>)map.get("albums");
    }

    @Override
    public ArrayList<Album> getAlbumBySingerId(String singerid){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",singerid);
        albumMapper.getAlbumBySingerId(map);
        return (ArrayList<Album>)map.get("albums");
    }

    public List<Object> getAlbumRankList(){
        Map<String,List<Object>> map = new HashMap<>();
        songMapper.getAlbumRankList(map);
        return map.get("albumrank");
    }
}
