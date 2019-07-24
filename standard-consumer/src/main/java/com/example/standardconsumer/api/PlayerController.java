package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.service.PlayerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/standard-consumer")
public class PlayerController
{
    @Autowired
    private PlayerService playerService;

    @UserLog("PlayerController")
    @RequestMapping(value = "/play",method = RequestMethod.POST)
    public void play(HttpServletRequest request,@Param("mode")Integer mode)
    {
        if(request.getSession().getAttribute("playerLoaded").equals(0))//还没有播放器界面
        {
            ModelAndView modelAndView=new ModelAndView("Player");
        }
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/closePlayer",method = RequestMethod.GET)
    public void closePlayer(HttpServletRequest request)
    {
        request.getSession().removeAttribute("playerLoaded");
        request.getSession().removeAttribute("playList");
        request.getSession().removeAttribute("playList_mode");
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/getPlayer",method = RequestMethod.GET)
    public ModelAndView getPlayer(HttpServletRequest request)
    {
        ModelAndView modelAndView=new ModelAndView("Player");
        ArrayList<Integer>playList=(ArrayList<Integer>) request.getSession().getAttribute("playList");
        ArrayList<Song>songList=new ArrayList<>();

        for(Integer i:playList)
        {
            HashMap hashMap=new HashMap();
            hashMap.put("songid",i);
            Song temp=playerService.getSongByID(hashMap);
            songList.add(temp);
        }
        modelAndView.addObject("songList",songList);
        return modelAndView;
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/addSongToList",method = RequestMethod.POST)
    public Map addSongToList(@Param("songID")Integer songID, HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("playList")==null)
        {
            ArrayList<Integer> arrayList=new ArrayList<>();
            ArrayList<Integer> arrayList_mode=new ArrayList<>();
            session.setAttribute("playList",arrayList);
            session.setAttribute("playList_mode",arrayList_mode);
        }
        ArrayList<Integer> arrayList=(ArrayList<Integer>) session.getAttribute("playList");
        ArrayList<Integer> arrayList_mode=(ArrayList<Integer>)session.getAttribute("playList_mode");
        if(arrayList.indexOf(songID)==-1)
        {
            arrayList.add(songID);
            arrayList_mode.add(songID);
        }
        else
            return null;

        if(session.getAttribute("playerLoaded")==null)//还没有播放器界面
        {
            session.setAttribute("playerLoaded",1);
            HashMap hashMap=new HashMap();
            hashMap.put("urllink","/getPlayer");
            return hashMap;
        }
        HashMap hashMap=new HashMap();
        hashMap.put("newSong",songID);
        return hashMap;
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/addAlbumToList",method = RequestMethod.POST)
    public Map addAlbumToList(@Param("albumID")Integer albumID,HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("playList")==null)
        {
            ArrayList<Integer> arrayList=new ArrayList<>();
            session.setAttribute("playList",arrayList);
        }
        ArrayList<Integer> arrayList=(ArrayList<Integer>) session.getAttribute("playList");

        HashMap<String,Object>hashMap =new HashMap<>();
        hashMap.put("albumid",albumID);
        ArrayList<Integer>album=playerService.getListByAlbumID(hashMap);
        if(album==null||album.size()==0)
        {
            return null;
        }
        for(Integer i:album)
        {
            if(arrayList.indexOf(i)==-1)
                arrayList.add(i);
        }

        if(session.getAttribute("playerLoaded")==null)
        {
            session.setAttribute("playerLoaded",1);
            HashMap map=new HashMap();
            map.put("urllink","/getPlayer");
            return map;
        }
        return null;

    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/addSongListToList",method = RequestMethod.POST)
    public Map addSongListToList(@Param("songListID")Integer songListID,HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("playList")==null)
        {
            ArrayList<Integer> arrayList=new ArrayList<>();
            ArrayList<Integer> arrayList_mode=new ArrayList<>();
            session.setAttribute("playList",arrayList);
            session.setAttribute("playList_mode",arrayList_mode);
        }
        ArrayList<Integer> arrayList=(ArrayList<Integer>) session.getAttribute("playList");
        ArrayList<Integer> arrayList_mode=(ArrayList<Integer>)session.getAttribute("playList_mode");

        HashMap<String,Object>hashMap =new HashMap<>();
        hashMap.put("songlistid",songListID);
        ArrayList<Integer>songList=playerService.getListByListID(hashMap);
        if(songList==null||songList.size()==0)
        {
            return null;
        }
        ArrayList<Integer>addList=new ArrayList<>();
        for(Integer i:songList)
        {
            if(arrayList.indexOf(i)==-1)
            {
                addList.add(i);
                arrayList.add(i);
                arrayList_mode.add(i);
            }
        }
        if(session.getAttribute("playerLoaded")==null)
        {
            session.setAttribute("playerLoaded",1);
            HashMap map=new HashMap();
            map.put("urllink","/getPlayer");
            return map;
        }
        HashMap returnMap=new HashMap();
        returnMap.put("newSongList",addList);
        return returnMap;
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/getNextSong",method = RequestMethod.POST)
    public Song getNextSong(@Param("songID") String songID,HttpServletRequest request)
    {
        ArrayList<Integer>playList=(ArrayList<Integer>) request.getSession().getAttribute("playList_mode");
        HashMap hashMap=new HashMap();
        Integer temp=playList.indexOf(Integer.parseInt(songID));//当前歌曲的index
        if(temp.equals(playList.size()-1))//当前是最后一首
        {
            hashMap.put("songid",playList.get(0));
        }
        else
        {
            hashMap.put("songid",playList.get(temp+1));
        }
        return playerService.getSongByID(hashMap);
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/getLastSong",method = RequestMethod.POST)
    public Song getLastSong(@Param("songID")String songID,HttpServletRequest request)
    {
        ArrayList<Integer>playList=(ArrayList<Integer>) request.getSession().getAttribute("playList_mode");
        HashMap hashMap=new HashMap();
        Integer temp=playList.indexOf(Integer.parseInt(songID));//当前歌曲的index
        if(temp.equals(0))//当前是第一首
        {
            hashMap.put("songid",playList.get(playList.size()-1));
        }
        else
        {
            hashMap.put("songid",playList.get(temp-1));
        }
        return playerService.getSongByID(hashMap);
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/changePlayMode",method = RequestMethod.POST)
    public void changePlayMode(@Param("mode")String mode,@Param("songID")String songID, HttpServletRequest request)
    {
        ArrayList<Integer>playlist=(ArrayList<Integer>)request.getSession().getAttribute("playList");
        request.getSession().removeAttribute("playList_mode");
        if(mode.equals("0"))
        {
            //随机播放
            ArrayList<Integer>temp=new ArrayList<>(playlist);
            Collections.shuffle(temp);
            request.getSession().setAttribute("playList_mode",temp);
        }
        else if(mode.equals("1"))
        {
            //单曲循环
            ArrayList<Integer>temp=new ArrayList<>();
            temp.add(Integer.parseInt(songID));
            request.getSession().setAttribute("playList_mode",temp);
        }
        else
        {
            //顺序播放
            ArrayList<Integer>temp=new ArrayList<>(playlist);
            request.getSession().setAttribute("playList_mode",temp);
        }
    }

    @UserLog("PlayerController")
    @RequestMapping(value = "/api/getSongByID",method = RequestMethod.POST)
    public Song getSongByID(@Param("songID")String songID)
    {
        HashMap hashMap=new HashMap();
        hashMap.put("songid",songID);
        Song temp=playerService.getSongByID(hashMap);
        return temp;
    }

    @UserLog("PlayerController")
    @PostMapping(value = "/api/addSearchSong")
    public Integer addSong(String songID,String path,String name,String image,String length,String albumID,String albumName,String singer,String lrc,String singerID)
    {
        return playerService.addSong(songID,path,name,image,length,albumID,albumName,singer,lrc,singerID);
    }

    @UserLog("PlayerController")
    @GetMapping(value = "/api/getUserRecommend")
    public Object getUserRecommend(String userID)throws Exception{
        Map tempMap=new HashMap();
        tempMap.put("recommend",playerService.getRecommendSingers(userID));
        return tempMap;
    }

    @UserLog("PlayerController")
    @PostMapping(value = "/api/addUserHistory")
    public Object add(String userID,String singerID){
        return playerService.addUserHistory(userID,singerID);
    }

}
