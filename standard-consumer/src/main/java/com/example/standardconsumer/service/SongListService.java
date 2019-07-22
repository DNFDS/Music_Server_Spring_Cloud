package com.example.standardconsumer.service;

import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;

import javax.sql.rowset.spi.SyncResolver;
import java.util.ArrayList;

public interface SongListService {
    ArrayList<SongList> getCommandSongList(String userid);
    SongList getSongListById(String songlistid);
    String createNewSongList(String name, String image, String isprivate,String userid);
    String deleteSongList(String songlistid);
    boolean deleteSongInList(String songid,String songlistid);
    String changeSongListName(String name,String songlistid);
    ArrayList<SongList> getSongListByNamePart(String name);
    ResultEntity getSongsInSongList(String songlistid);
    ResultEntity getSongListSavedNum(String songlistid);
    ResultEntity getSingerInSongList(ArrayList<Song> songs);
    ResultEntity getAlbumsInSongList(ArrayList<Song> songs);
    String isSonglistSaved(String userid,String songlistid);
}
