package com.example.standardconsumer.api;


import com.example.standardconsumer.domain.*;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.service.*;
import com.example.standardconsumer.util.AutoShowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/standard-consumer")
public class DetailController {

    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private SongListService songListService;
    @Autowired
    private AutoShowUtil showUtil;
    @Autowired
    private SingerService singerService;
    @Autowired
    private PlayerService playerService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value ="/api/getSongAndAlbumOfSinger",method = RequestMethod.GET)
    public Object singerDetail(@RequestParam("singerid") String singerid, @RequestParam("userid") String userid){
        Map<String, Object> map = new HashMap<>();
        ArrayList<Song> songs = singerService.getSingerSong(singerid);
        if(songs.size()>5){
            // 取前五个歌曲加入map
            map.put("songs",songs.subList(0,4));
        }else {
            map.put("songs",songs);
        }
        Singer singer = singerService.getSingerById(singerid);
        ArrayList<Album> albums = singerService.getSingerAlbum(singerid);
        Boolean isfollow = false;
        if (userid != null && !userid.isEmpty()) {
            isfollow = singerService.isUserLikeSinger(singerid, userid);
        }
        map.put("isfollow",isfollow);
        map.put("albumNum",albums.size());
        map.put("songNum", songs.size());
        map.put("singer", singer);
        return map;
    }

    @RequestMapping(value ="/Song",method = RequestMethod.GET)
    public String songDetail(@RequestParam("songid") String songid, Map<String, Object> map,HttpServletRequest request){
        Song song = songService.getSongById(songid);
        ArrayList<comments> comments = songService.getCommentsInSong(songid);
        Album album = albumService.getAlbumByAlbumId(song.getAlbumid());
        map.put("song",song);
        //map.put("singers",singers);
        map.put("album",album);
        map.put("comments",comments);
        return "Details/song_detail";
    }
    @RequestMapping(value ="/SongList",method = RequestMethod.GET)
    public String songListDetail(@RequestParam("songlistid") String songlistid, Map<String, Object> map,HttpServletRequest request){
        SongList songList = songListService.getSongListById(songlistid);
        ResultEntity e = songListService.getSongsInSongList(songList.getSonglistid());
        ArrayList<Song>songs = (ArrayList<Song>)e.getObject();
        String style = showUtil.getSongListStyle(songs);
        map.putAll(showUtil.showSong(songs));
        e = songListService.getSongListSavedNum(songList.getSonglistid());
        int num = (int)e.getObject();
        e = userService.getUserById(songList.getUserid());
        User creater = (User)e.getObject();
        map.put("creater",creater);
        map.put("savenum",num);
        map.put("style",style);
        map.put("songlist",songList);
        return "Details/songlist_detail";
        //"songs" "singers" "singername" "albums"
    }
    @RequestMapping(value ="/Album",method = RequestMethod.GET)
    public String albumDetail(@RequestParam("albumid") String albumid, Map<String, Object> map,HttpServletRequest request){
        Album album = albumService.getAlbumByAlbumId(albumid);
        ArrayList<Song> songs = albumService.getSongsInAlbum(albumid);
        ResultEntity e;
        e = songListService.getSingerInSongList(songs);
        map.put("singers",e.getObject());
        ArrayList<String>singername = showUtil.unionSingers((ArrayList<ArrayList<Singer>>)e.getObject());
        map.put("singername",singername);
        Singer author = singerService.getSingerById(album.getSingerid());
        String style = showUtil.getSongListStyle(songs);
        map.put("style",style);
        map.put("author",author);
        map.put("songs",songs);
        map.put("album",album);
        return "Details/album_detail";
    }
    @ResponseBody
    @RequestMapping(value ="/Comment",method = RequestMethod.POST)
    public String Comment(HttpServletRequest request, @RequestParam("words") String words,@RequestParam("songid") String songid){
        User user = (User) request.getSession(false).getAttribute("user");
        boolean succ = songService.commentSong(words,songid,user.getUserid());
        if(succ)
            return "评论成功";
        else
            return "评论失败";
    }
    @RequestMapping(value ="/User",method = RequestMethod.GET)
    public String showUser(@RequestParam("userid") String userid, Map<String, Object> map,HttpServletRequest request){
        ResultEntity e = userService.getUserById(userid);
        if(e.getSuccess()){
            request.getSession().setAttribute("visted",e.getObject());
        }
        return "redirect:/profile/like_song";
    }

}
