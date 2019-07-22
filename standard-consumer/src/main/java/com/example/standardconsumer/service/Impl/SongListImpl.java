package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Singer;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.feignApi.AlbumServiceApi;
import com.example.standardconsumer.feignApi.SingerServiceApi;
import com.example.standardconsumer.feignApi.SongListServiceApi;
import com.example.standardconsumer.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SongListImpl implements SongListService {

    @Autowired
    SongListServiceApi songListServiceApi;

    @Autowired
    AlbumServiceApi albumServiceApi;

    @Autowired
    SingerServiceApi singerServiceApi;

    @Override
    public SongList getSongListById(String songlistid){
        ArrayList<SongList> songLists = songListServiceApi.getSongListById(songlistid);
        SongList songList;
        if(songLists.size()==0){
            songList = new SongList();
        }else {
            songList = songLists.get(0);
        }
        return songList;
    }
    @Override
    public ResultEntity getSongsInSongList(String songlistid){
        ArrayList<Song> songs = songListServiceApi.getSongsInSongList(songlistid);
        System.out.println(songlistid);
        for(int i=0; i<songs.size(); i++){

        }
        return new ResultEntity(true,"",songs);
    }

    @Override
    public ResultEntity getSingerInSongList(ArrayList<Song> songs){
        ArrayList<ArrayList<Singer>> singers = new ArrayList<>();
        for(Song song :songs){
            ArrayList<Singer> singer = singerServiceApi.getSongSinger(song.getSongid());
            singers.add(singer);
        }
        return new ResultEntity(true,"",singers);
    }

    @Override
    public ResultEntity getAlbumsInSongList(ArrayList<Song> songs){
        ArrayList<Album>albums = new ArrayList<>();
        for(Song song:songs){
            Album album;
            ArrayList<Album> i = albumServiceApi.getAlbumByAlbumId(song.getAlbumid());
            if(i.size() == 0){//如果当前歌曲没有专辑的话，就给他一个空专辑替位
                album = new Album();
                album.setAlbumid("null");
                album.setAlbumname("无");
            }
            else{
                album = i.get(0);
            }
            albums.add(album);
        }
        return new ResultEntity(true,"",albums);
    }

    @Override
    public ResultEntity getSongListSavedNum(String  songlistid){
        int num = songListServiceApi.getSongListSavedNum(songlistid);
        return new ResultEntity(true,"",num);
    }
    public ArrayList<SongList> getSongListByNamePart(String name){
        ArrayList<SongList> songLists = songListServiceApi.getSongListByNamePart(name);
        return songLists;
    }

    @Override
    public String createNewSongList(String name, String image, String isprivate,String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("songlistname",name);
        map.put("songlistimage",image);
        map.put("isprivate",isprivate);
        map.put("userid",userid);
        return songListServiceApi.createSongList(map);
    }

    @Override
    public ArrayList<SongList> getCommandSongList(String userid){
        return songListServiceApi.getPushSonglist(userid);
    }

    @Override
    public String deleteSongList(String songlistid){
        return songListServiceApi.deleteSongList(songlistid);
    }

    @Override
    public String changeSongListName(String name,String songlistid){
        return songListServiceApi.updateSongListName(name,songlistid);
    }

    public boolean deleteSongInList(String songid,String songlistid){
        return songListServiceApi.deleteSongFromSongList(songid,songlistid);
    }

    public String isSonglistSaved(String userid,String songlistid){
        return songListServiceApi.isSonglistSaved(userid,songlistid);
    }
}
