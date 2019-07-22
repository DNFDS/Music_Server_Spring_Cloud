package com.example.demo.service;

import com.example.demo.entity.Song;
import com.example.demo.entity.SongList;
import com.example.demo.entity.result.ResultEntity;

import java.util.ArrayList;
import java.util.Map;

public interface SongListService {
    ArrayList<SongList> getCommandSongList(String userid);
    ArrayList<SongList> getSongListById(String songlistid);
    String createNewSongList(String name, String image, String isprivate,String userid);
    String deleteSongList(String songlistid);
    boolean deleteSongInList(String songid,String songlistid);
    String changeSongListName(String name,String songlistid);
    ArrayList<SongList> getSongListByNamePart(String name);
    ArrayList<Song> getSongsInSongList(String songlistid);
    int getSongListSavedNum(String songlistid);
    ResultEntity getSingerInSongList(ArrayList<Song> songs);
    ResultEntity getAlbumsInSongList(ArrayList<Song> songs);
    String isSonglistSaved(String userid,String songlistid);
}
