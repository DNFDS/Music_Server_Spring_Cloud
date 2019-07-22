package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.feignApi.AlbumServiceApi;
import com.example.standardconsumer.feignApi.RankServiceApi;
import com.example.standardconsumer.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumImpl implements AlbumService {

    @Autowired
    AlbumServiceApi albumServiceApi;
    @Autowired
    RankServiceApi rankServiceApi;
    @Override
    public Album getAlbumByAlbumId(String albumid){
        Map<String,Object> map = new HashMap<>();
        ArrayList<Album> albums = albumServiceApi.getAlbumByAlbumId(albumid);
        if(albums.size()==0){
            Album album = new Album();
            album.setAlbumname("null");
            album.setAlbumid("null");
            return album;
        }
        return albums.get(0);
    }
    @Override
    public ArrayList<Song> getSongsInAlbum(String albumid){
        ArrayList<Song> songs = albumServiceApi.getSongsInAlbum(albumid);
        return songs;
    }
    @Override
    public ArrayList<Album> getAlbumByNamePart(String name){
        ArrayList<Album> albums = albumServiceApi.getAlbumByNamePart(name);
        return albums;
    }
    @Override
    public ArrayList<Album> getCommandAlbum(String userid){
        ArrayList<Album> albums = albumServiceApi.getCommandAlbum(userid);
        return albums;
    }
    @Override
    public List<Object> getAlbumRankList() {
        return rankServiceApi.getAlbumRankCache();
    }

}
