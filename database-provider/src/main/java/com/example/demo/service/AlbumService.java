package com.example.demo.service;

import com.example.demo.entity.Album;
import com.example.demo.entity.Song;

import java.util.ArrayList;
import java.util.List;

public interface AlbumService {
    public ArrayList<Album> getCommandAlbum(String userid);
    public ArrayList<Album> getAlbumByAlbumId(String albumid);
    public ArrayList<Album> getAlbumByNamePart(String name);
    public ArrayList<Song> getSongsInAlbum(String albumid);
    public ArrayList<Album> getAlbumBySingerId(String singerid);
    List<Object> getAlbumRankList();
}
