package com.example.demo.service.Impl;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.service.PlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private SongMapper songMapper;
    @Resource
    private SongListMapper songListMapper;
    @Resource
    private AlbumMapper albumMapper;
    @Resource
    private SingerMapper singerMapper;
    @Resource
    private HistoryMapper historyMapper;

    @Override
    public ArrayList<Song> getSongByID(Map<String, Object> Map)
    {
        System.out.println(Map);
        songMapper.getSongById(Map);
        ArrayList<Song> temp=(ArrayList<Song>) Map.get("songs");
        return temp;
    }

    @Override
    public ArrayList<Song> getListByAlbumID(Map<String, Object> Map)
    {
        songMapper.getSongByAlbumId(Map);
        ArrayList<Song> arrayList=(ArrayList<Song>)Map.get("songs");
        return arrayList;
    }

    @Override
    public ArrayList<SongList> getListByListID(Map<String, Object> Map)
    {
        songListMapper.getSongListById(Map);
        ArrayList<SongList> arrayList=(ArrayList<SongList>)Map.get("songlist");
        HashMap hashMap=new HashMap();
        return arrayList;
    }

    @Override
    public ArrayList<SongList> getSongsInSongList(Map<String, Object> Map){
        songListMapper.getSongsInSongList(Map);
        ArrayList<SongList> arrayList=(ArrayList<SongList>)Map.get("songlist");
        HashMap hashMap=new HashMap();
        return arrayList;
    }

    @Override
    public Integer addSong(Song song) {
        Map<String,Object>tempSongMap=new HashMap<>();
        tempSongMap.put("song",song.getSongid());
        songMapper.getSongById(tempSongMap);
        ArrayList<Song>tempSongList=(ArrayList<Song>) tempSongMap.get("songs");
        if (tempSongList.size()!=0)
            return -1;

        Song song1=new Song();
        song1.setAlbumid(song.getAlbumid()!=null?song.getAlbumid():"-1");
        song1.setAdminid("1002");
        song1.setCompany(song.getSongname().length()%3==0?"索尼音乐":song.getSongname().length()%3==1?"腾讯音乐":"网易音乐");
        song1.setFree("1");
        song1.setLanguage("中文");
        song1.setLength(song.getLength()!=null?song.getLength():"-1");
        song1.setLyric(song.getLyric());
        song1.setPlaytimes(0);
        song1.setSavenum(new BigDecimal(0));
        song1.setSinger(song.getSinger()!=null?song.getSinger():"-1");
        song1.setSongage("00");
        song1.setSongimage(song.getSongimage());
        song1.setSongname(song.getSongname());
        song1.setSongpath(song.getSongpath());
        song1.setSongschool(Integer.parseInt(song.getLength())%3==0?"古典":Integer.parseInt(song.getLength())%3==1?"流行":"嘻哈");
        song1.setSingerID(song.getSingerID());
        song1.setSongid(song.getSongid());
        Integer result=songMapper.addSong(song);
        Integer result1=0;
        Integer result2=0;

        Map<String,Object> tempMapSinger=new HashMap();
        tempMapSinger.put("singerid",song.getSingerID());
        singerMapper.getSingerById(tempMapSinger);
        ArrayList<Singer> tempSingerList=(ArrayList<Singer>) tempMapSinger.get("singers");
        if (tempSingerList.size()==0)
        {
            Singer tempSinger=new Singer();
            tempSinger.setSingerid(song.getSingerID());
            tempSinger.setAdminid("1002");
            tempSinger.setSingername(song.getSinger());
            tempSinger.setIntroduction(" ");
            tempSinger.setRegion(" ");
            tempSinger.setSingersex("女");
            tempSinger.setSingerimage(" ");

            result1=singerMapper.addSinger(tempSinger);
        }

        Map<String,Object> tempMap=new HashMap();
        tempMap.put("albumid",song.getAlbumid());
        albumMapper.getAlbumById(tempMap);
        ArrayList<Album>albums=(ArrayList<Album>) tempMap.get("albums");
        if (albums.size()==0)
        {
            Album album=new Album();
            album.setAlbumid(album.getAlbumid());
            album.setAlbumname(album.getAlbumname());
            album.setAdminid("1002");
            album.setAlbumage("00");
            album.setCompany(song.getCompany());
            album.setFree("1");
            album.setLanguage("中文");
            album.setSingerid(song.getSingerID());
            album.setAlbumimage(album.getAlbumage());

            result2=albumMapper.addAlbum(album);
        }

        return result+result1+result2;
    }

    @Override
    public Integer addUserHistory(String userID, String singerID)
    {
        Integer result=0;
        History history=historyMapper.getHistoryByUserAndSinger(userID,singerID);
        if (history==null)
            result=historyMapper.addUserHistory(userID,singerID);
        else
            result=historyMapper.updateHistory(userID,singerID);
        return result;
    }

    @Override
    public String[] getRecommendSingers(String userID)throws Exception
    {
        List<History>history=historyMapper.getAllHistory();
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
