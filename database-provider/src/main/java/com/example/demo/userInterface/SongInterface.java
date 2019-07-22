package com.example.demo.userInterface;

import com.example.demo.entity.*;
import com.example.demo.entity.result.ResultEntity;
import com.example.demo.service.*;
import com.example.demo.util.AutoShowUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class SongInterface {
    @Autowired
    private UserService userService;
    @Autowired
    private SongListService songListService;
    @Autowired
    private AutoShowUtil showUtil;
    @Autowired
    private KeepService keepService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    @GetMapping(value = "/api/getFavorite")
    public Object getFavorite(@Param("id")String id){
        SongList i = userService.getFavoritelist(id);
        return songListService.getSongsInSongList(i.getSonglistid()).getObject();
    }
    @GetMapping(value="/api/getSongsInList")
    public List<Song> getSongsInList(@Param("songListId") String songListId){
        return (List<Song>)songListService.getSongsInSongList(songListId).getObject();
    }
    @PostMapping(value = "/api/buildSong")
    public Object buildSong(@RequestBody Map msgArr){
        ArrayList<String> albumid = (ArrayList<String>)msgArr.get("albumid");
        ArrayList<String> songid = (ArrayList<String>)msgArr.get("songid");
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Album> albums = new ArrayList<>();
        ArrayList<Song> songs = new ArrayList<>();
        for(String album : albumid) {
            albums.add(albumService.getAlbumByAlbumId(album));
        }
        for(String song : songid) {
            songs.add(songService.getSongById(song));
        }
        map.put("albums", albums);
        map.put("songs", songs);
        return map;
    }

    @GetMapping(value="/api/getSong")
    public Object getSong(@RequestParam("songid") String songid){
        return songService.getSongById(songid);
    }

    @GetMapping(value = "/api/favoriteSong")
    public ResultEntity favoriteSong(@Param("songid")String songid, @Param("userid")String userid){
        SongList favorite = userService.getFavoritelist(userid);
        boolean succ = true;
        if(favorite == null){
            succ = false;
            return new ResultEntity(succ,"用户默认歌单不存在",null);
        }
        String result = keepService.KeepSong(songid,favorite.getSonglistid());
        if(result.equals("0")){
            succ = false;
            return new ResultEntity(succ,"保存失败,歌曲已存在",null);
        }
        return new ResultEntity(succ,"喜欢成功",null);
    }

    @GetMapping(value = "/api/KeepSong")
    public ResultEntity KeepSong(@Param("songlistid")String songlistid, @Param("songid")String songid){
        String result = keepService.KeepSong(songid,songlistid);
        boolean succ = true;
        if(result.equals("0")){
            succ = false;
            return new ResultEntity(succ,"保存失败,歌曲已存在",null);
        }
        return new ResultEntity(succ,"保存成功",null);
    }

    @GetMapping(value = "/api/unKeepSong")
    public ResultEntity unKeepSong(@Param("songlistid")String songlistid, @Param("songid")String songid){
        String result = keepService.unKeepSong(songid,songlistid);
        boolean succ = true;
        if(result.equals("0")){
            succ = false;
            return new ResultEntity(succ,"取消保存失败",null);
        }
        return new ResultEntity(succ,"取消保存成功",null);
    }

    @RequestMapping(value="/api/commentSong", method = RequestMethod.POST)
    public boolean commentSong(@RequestParam("songID") String songID, @RequestParam("userID") String userID,
                               @RequestParam("commentText") String commentText, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(commentText);
        return songService.commentSong(commentText,songID,userID);
    }

    @RequestMapping(value="/api/getSongInfo", method = RequestMethod.GET)
    public Map<String, Object> getSongInfoBySongID(@RequestParam("songId") String songID){
        Song song = songService.getSongById(songID);
        ArrayList<comments> comments = songService.getCommentsInSong(songID);
        Album album = albumService.getAlbumByAlbumId(song.getAlbumid());
        List<String> commentUsers = new ArrayList<>();
        for(int i=0; i<comments.size(); i++){
            String userID = comments.get(i).getUserid();
            ResultEntity re = userService.getUserById(userID);
            User u = (User)re.getObject();
            commentUsers.add(u.getUsername());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("songInfo", song);
        map.put("commentsList", comments);
        map.put("albumName", album.getAlbumname());
        map.put("albumIssueTime",album.getAlbumage());
        map.put("commentUsers", commentUsers);
        return map;
    }

    @RequestMapping(value = "/profile/like_song_song_typeList", method = RequestMethod.GET)
    public ModelAndView showSongsInList(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String flag = request.getParameter("flag");
        ResultEntity e;
        Map<String,Object> map = new HashMap<>();
        User user = new User();
        user.setUserid(id);
        e = userService.getSongLists(user);
        Map<String,Object>e_map = (Map<String, Object>) e.getObject();
        ArrayList<SongList> createdsonglist = (ArrayList<SongList>)e_map.get("createdsonglist");
        getFavoriteList(map,createdsonglist);
        ArrayList<Song> songs = (ArrayList<Song>) map.get("songs");
        map.putAll(showUtil.showSong(songs));
        if(flag.equals("2"))
            return new ModelAndView("temp/mylike/song_list_details",map);
        return new ModelAndView("temp/mylike_main",map);
    }


    private void getFavoriteList(Map<String,Object> map,ArrayList<SongList> createdsonglist){
        ResultEntity e;
        for(SongList i : createdsonglist){
            if(i.getSonglistname().equals("我喜欢")){
                map.put("favoritesonglist",i);
                e = songListService.getSongsInSongList(i.getSonglistid());
                map.put("songs",e.getObject());
                break;
            }
        }
    }

    @GetMapping(value = "/api/isSongBought")
    public String isSongBought(@Param("songid") String songid,@Param("albumid") String albumid,@Param("userid") String userid){
        return songService.isSongBought(songid,albumid,userid);
    }
}
