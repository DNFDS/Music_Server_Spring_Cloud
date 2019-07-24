package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.service.KeepService;
import com.example.standardconsumer.service.SongListService;
import com.example.standardconsumer.service.UserService;
import com.example.standardconsumer.util.AutoShowUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/standard-consumer")
public class SongListController {
    @Autowired
    private UserService userService;
    @Autowired
    private SongListService songListService;
    @Autowired
    private KeepService keepService;
    @Autowired
    private AutoShowUtil showUtil;

    @UserLog("SongLsitController")
    @GetMapping(value = "/api/getSongListSavedNum")
    public Object getSongListSavedNum(@Param("songlistid") String songlistid){
        return songListService.getSongListSavedNum(songlistid).getObject();
    }

    @UserLog("SongLsitController")
    @GetMapping(value="/api/getSongListById")
    public SongList getSongListById(@Param("id") String id){
        return songListService.getSongListById(id);
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/api/getNumOfSongList")
    public Object getNumOfSongList(@Param("songlistid") String songlistid){
        ArrayList<Song> songs = (ArrayList<Song>)songListService.getSongsInSongList(songlistid).getObject();
        return songs.size();
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/api/getSongsOfSongList")
    public Object getSongsOfSongList(@Param("songlistid") String songlistid){
        ArrayList<Song> songs = (ArrayList<Song>)songListService.getSongsInSongList(songlistid).getObject();
        return songs;
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/api/showCreatedSongList")
    public Object showCreatedSongList(@Param("id") String id){
        //如果请求的form页面，就直接返回
        //list页面，要额外添加歌单的收藏数和曲目数
        return userService.getCreatedSongList(id);
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/api/KeepSongList")
    public ResultEntity KeepSongList(@Param("userid")String userid, @Param("songlistid")String songlistid){
        String result = keepService.KeepSongList(songlistid,userid);
        boolean succ = true;
        if(result.equals("0")){
            succ = false;
            return new ResultEntity(succ,"歌单已收藏",null);
        }
        return new ResultEntity(succ,"收藏成功",null);
    }

    @UserLog("SongLsitController")
    @PostMapping(value="/api/keepSongListPost")
    public Boolean KeepSongListPost(@Param("userId") String userId, @Param("songListId") String songListId,
                                    HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(userId+songListId);
        boolean succ = true;
        String result = keepService.KeepSongList(songListId, userId);
        //失败
        if(result.equals("0")){
            succ = false;
        }
        return succ;
    }

    @UserLog("SongLsitController")
    @GetMapping(value="/api/cancelKeepSongList")
    public Boolean CancelKeepSongList(@Param("userid") String userid, @Param("songlistid") String songlistid){
        boolean succ = true;
        System.out.println(userid + ' ' + songlistid);
        String result = keepService.unKeepSongList(songlistid, userid);
        if(result.equals("0")){
            succ = false;
        }
        return succ;
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/api/createNewSonglist")
    public String createNewSonglist(@Param("name")String name, @Param("userid") String userid) {
        String id = songListService.createNewSongList(name,null,null,userid);
        return "您新创建的歌单id为: "+id;
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/profile/like_song_songlist_typeList")
    public ModelAndView showKeepedSongList(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession(false).getAttribute("visted");
        String flag = request.getParameter("flag");
        //如果请求的form页面，就直接返回
        //list页面，要额外添加歌单的收藏数和曲目数
        ArrayList<SongList> keepedSongList = userService.getKeepedSongList(user.getUserid());
        //得到所有歌单的曲目数和收藏数创建人  songnum  savenum username
        Map<String,Object> map = showUtil.showSongList(  keepedSongList);
        if(flag.equals("1"))
            return new ModelAndView("temp/mylike/songlist_details",map);
        if(flag.equals("2"))
            return new ModelAndView("temp/mylike/songlist_form_details",map);
        return new ModelAndView("temp/mylike/songlist_list_details",map);
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/getMySongList")
    public ModelAndView getMySongList(HttpServletRequest request, @RequestParam("songid") String songid){
        User user = (User) request.getSession(false).getAttribute("user");
        ResultEntity e = userService.getSongLists(user);
        Map<String,Object>e_map = (Map<String, Object>) e.getObject();
        ArrayList<SongList> createdsonglist = (ArrayList<SongList>)e_map.get("createdsonglist");
        Map<String,Object>map = new HashMap<>();
        map.put("songlists",createdsonglist);
        map.put("toAdd",songid);
        return new ModelAndView("temp/songListChooser",map);
    }

    @UserLog("SongLsitController")
    @GetMapping(value = "/api/isSonglistSaved")
    public String isSonglistSaved(@Param("userid") String userid,@Param("songlistid") String songlistid){
        return songListService.isSonglistSaved(userid, songlistid);
    }
}