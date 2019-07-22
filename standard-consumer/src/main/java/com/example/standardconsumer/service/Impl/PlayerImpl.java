package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.domain.*;
import com.example.standardconsumer.feignApi.AlbumServiceApi;
import com.example.standardconsumer.feignApi.HistoryServiceApi;
import com.example.standardconsumer.feignApi.PlayerServiceApi;
import com.example.standardconsumer.feignApi.SingerServiceApi;
import com.example.standardconsumer.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerImpl implements PlayerService
{
    @Autowired
    PlayerServiceApi playerServiceApi;

    @Autowired
    AlbumServiceApi albumServiceApi;

    @Autowired
    HistoryServiceApi historyServiceApi;

    @Autowired
    SingerServiceApi singerServiceApi;

    @Override
    public Song getSongByID(Map<String, Object> Map)
    {
        ArrayList<Song> songs =playerServiceApi.getSongByID(Map);
        return songs.get(0);
    }

    @Override
    public ArrayList<Integer> getListByAlbumID(Map<String, Object> Map)
    {
        ArrayList<Song> arrayList=playerServiceApi.getListByAlbumID(Map);
        ArrayList<Integer> integerArrayList=new ArrayList<>();
        for(Song i:arrayList)
        {
            integerArrayList.add(Integer.parseInt(i.getSongid()));
        }
        return integerArrayList;
    }

    @Override
    public ArrayList<Integer> getListByListID(Map<String, Object> Map)
    {
        ArrayList<SongList> arrayList=playerServiceApi.getListByListID(Map);
        HashMap hashMap=new HashMap();
        hashMap.put("songlistid",arrayList.get(0).getSonglistid());
        ArrayList<Song>songList=playerServiceApi.getSongsInSongList(hashMap);
        ArrayList<Integer> integerArrayList=new ArrayList<>();
        for(Song i:songList)
        {
            integerArrayList.add(Integer.parseInt(i.getSongid()));
        }
        return integerArrayList;
    }

    @Override
    public Integer addSong(String songID,String path, String name, String image, String length, String albumID,String albumName, String singer, String lrc,String singerID) {
        Map<String,Object>tempSongMap=new HashMap<>();
        tempSongMap.put("songid",songID);;
        ArrayList<Song>tempSongList=playerServiceApi.getSongByID(tempSongMap);
        if (tempSongList.size()!=0)
            return -1;

        Song song=new Song();
        song.setAlbumid(albumID!=null?albumID:"-1");
        song.setAdminid("1002");
        song.setCompany(name.length()%3==0?"索尼音乐":name.length()%3==1?"腾讯音乐":"网易音乐");
        song.setFree("1");
        song.setLanguage("中文");
        song.setLength(length!=null?length:"-1");
        song.setLyric(lrc);
        song.setPlaytimes(0);
        song.setSavenum(new BigDecimal(0));
        song.setSinger(singer!=null?singer:"-1");
        song.setSongage("00");
        song.setSongimage(image);
        song.setSongname(name);
        song.setSongpath(path);
        song.setSongschool(Integer.parseInt(length)%3==0?"古典":Integer.parseInt(length)%3==1?"流行":"嘻哈");
        song.setSingerID(singerID);
        song.setSongid(songID);
        Integer result=playerServiceApi.addSong(song);
        Integer result1=0;
        Integer result2=0;

        ArrayList<Singer> tempSingerList=singerServiceApi.getSingerById(singerID);
        if (tempSingerList.size()==0)
        {
            Singer tempSinger=new Singer();
            tempSinger.setSingerid(singerID);
            tempSinger.setAdminid("1002");
            tempSinger.setSingername(singer);
            tempSinger.setIntroduction(" ");
            tempSinger.setRegion(" ");
            tempSinger.setSingersex("女");
            tempSinger.setSingerimage(" ");

            result1=singerServiceApi.addSinger(tempSinger);
        }

        Map<String,Object> tempMap=new HashMap();
        ArrayList<Album>albums=albumServiceApi.getAlbumByAlbumId(albumID);
        if (albums.size()==0)
        {
            Album album=new Album();
            album.setAlbumid(albumID);
            album.setAlbumname(albumName);
            album.setAdminid("1002");
            album.setAlbumage("00");
            album.setCompany(song.getCompany());
            album.setFree("1");
            album.setLanguage("中文");
            album.setSingerid(singerID);
            album.setAlbumimage(image);

            result2=albumServiceApi.addAlbum(album);
        }

        return result+result1+result2;
    }

    @Override
    public Integer addUserHistory(String userID, String singerID)
    {
        Integer result=0;
        History history=historyServiceApi.getHistoryByUserAndSinger(userID,singerID);
        if (history==null)
            result=historyServiceApi.addUserHistory(userID,singerID);
        else
            result=historyServiceApi.updateHistory(userID,singerID);
        return result;
    }

    @Override
    public String[] getRecommendSingers(String userID)throws Exception
    {
        List<History>history=historyServiceApi.getAllHistory();
        String userList="";
        String singerList="";
        String numList="";
        for(History i:history)
        {
            userList+=(i.getUserID()+",");
            singerList+=(i.getSingerID()+",");
            numList+=(i.getNum().toString()+",");
        }
        String[] recSingers=getRecomSingers(userList,singerList,numList,userID);
        return recSingers;
    }
    public String[] getRecomSingers(String userList,String singerList,String numList,String userID)throws Exception
    {
        //设置命令行传入的参数
        String[] arg = new String[]{"untitled1/venv/bin/python", "untitled1/recommend.py",userList,singerList,numList,userID,"5"};
        Process pr = Runtime.getRuntime().exec(arg);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line=in.readLine();
        in.close();
        pr.waitFor();
        line=line!=null?line:"";
        return line.split(",");
    }
}
