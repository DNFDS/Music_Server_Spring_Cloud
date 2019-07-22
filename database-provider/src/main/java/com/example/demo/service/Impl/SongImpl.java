package com.example.demo.service.Impl;

import com.example.demo.dao.BuyMapper;
import com.example.demo.dao.SongMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.CommentsMapper;
import com.example.demo.entity.*;
import com.example.demo.entity.result.ResultEntity;
import com.example.demo.service.SongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongImpl implements SongService {
    @Resource
    private SongMapper songMapper;
    @Resource
    private BuyMapper buyMapper;
    @Resource
    private CommentsMapper commentsMapper;
    @Resource
    private UserMapper uMapper;

    @Override
    public ArrayList<Singer> getSingersInSong(String songid){
        Map<String,Object> map = new HashMap<>();
        map.put("songid",songid);
        songMapper.getSongSinger(map);
        ArrayList<Singer> singers = (ArrayList<Singer>)map.get("singers");
        return singers;
    }

    @Override
    public ArrayList<Buy> getBoughtSongByUserId(String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        buyMapper.getBuyRecord(map);
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<Buy> buys = (ArrayList<Buy>)map.get("buyrecord");
        return buys;
    }

    @Override
    public Song getSongById(String songid){
        Map<String,Object> map = new HashMap<>();
        map.put("songid",songid);
        songMapper.getSongById(map);
        Song song;
        ArrayList<Song> songs = (ArrayList<Song>)map.get("songs");
        if(songs.size() == 0){
            song = new Song();
            song.setSongid("null");
            song.setSongname("null");
        }
        else song = songs.get(0);
        return song;
    }

    @Override
    public ArrayList<comments> getCommentsInSong(String songid){
        Map<String,Object> map = new HashMap<>();
        map.put("songid",songid);
        commentsMapper.getComment(map);
        ArrayList<comments> comments = (ArrayList<comments>)map.get("comments");
        if(comments == null){
            comments = new ArrayList<>();
        }
        return comments;
    }
    @Override
    public boolean commentSong(String words,String songid,String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("songid",songid);
        map.put("userid",userid);
        map.put("commenttext",words);
        commentsMapper.addComment(map);
        String result = (String)map.get("succ");
        if(result.equals("1"))
            return true;
        return false;
    }

    public ArrayList<Song> getSongByNamePart(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("songname",name);
        songMapper.getSongByNamePart(map);
        return (ArrayList<Song>) map.get("songs");
    }

    public ArrayList<Song> getCommandSong(String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        songMapper.getPushSong(map);
        return (ArrayList<Song>)map.get("songs");
    }

    public boolean playSong(String songid,String userid){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("songid",songid);
        songMapper.songPlaytimesPlus(map);
        String succ = (String)map.get("succ");
        return succ.equals("1");
    }

    public String isSongBought(String songid, String albumid, String userid){
        if (userid != null && !userid.isEmpty()) {
            Map<String,Object> umap = new HashMap<>();
            umap.put("userid",userid);
            uMapper.getUserById(umap);
            User u = ((ArrayList<User>)umap.get("result")).get(0);
            if(u.getIsvip().equals("1")){
                return "2";
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("musicid",songid);
        map.put("userid",userid);
        buyMapper.isMusicBought(map);
        String sb=(String)map.get("bought");
        map.remove("musicid");
        map.put("musicid",albumid);
        buyMapper.isMusicBought(map);
        String ab=(String)map.get("bought");
        String s=(sb.equals("1")||ab.equals("1"))?"1":"0";
        return s;
    }

    public List<Object> getSongRankList(){
        Map<String,List<Object>> map = new HashMap<>();
        songMapper.getSongRankList(map);
        return map.get("songrank");
    }
}
