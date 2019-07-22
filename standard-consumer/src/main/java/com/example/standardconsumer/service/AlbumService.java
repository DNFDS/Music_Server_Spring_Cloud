package com.example.standardconsumer.service;

import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Song;

import java.util.ArrayList;

public interface AlbumService {
    ArrayList<Album> getCommandAlbum(String userid);
    Album getAlbumByAlbumId(String albumid);
    ArrayList<Album> getAlbumByNamePart(String name);
    ArrayList<Song> getSongsInAlbum(String albumid);
}
