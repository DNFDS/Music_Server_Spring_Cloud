package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Singer;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.service.AlbumService;
import com.example.standardconsumer.service.SingerService;
import com.example.standardconsumer.service.SongListService;
import com.example.standardconsumer.service.SongService;
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
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private AutoShowUtil showUtil;
    @Autowired
    private SongListService songListService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;

    @UserLog("UserController")
    @GetMapping(value = "/api/recommend")
    public Map findMusic(@Param("userid") String userid){
        if(userid == "") {
            userid = "100001";
        }
        System.out.println(userid);
        Map<String, Object>map = new HashMap<>();
        ArrayList<Song> songs = songService.getCommandSong(userid);
        ArrayList<SongList> songLists = songListService.getCommandSongList(userid);
        ArrayList<Album> albums = albumService.getCommandAlbum(userid);
        if(songs.size()>5){
            map.put("songs",songs.subList(10,15));
        }
        else
            map.put("songs",songs);
        if(songLists.size()>5){
            map.put("songlists",songLists.subList(0,5));
        }
        else
            map.put("songlists",songLists);
        if(albums.size()>5){
            map.put("albums",albums.subList(0,5));
        }
        else
            map.put("albums",albums);

        return map;
    }

    @UserLog("UserController")
    @GetMapping(value = "/api/getUser")
    public Object getUser(@Param("id") String id) {
        return userService.getUserById(id).getObject();
    }

    @UserLog("UserController")
    @GetMapping(value = "/api/getFans")
    public Object getFans(@Param("id") String id) {
        User user = new User();
        user.setUserid(id);
        ResultEntity e = userService.getFans(user);
        return e.getObject();
    }

    @UserLog("UserController")
    @GetMapping(value = "/api/getFriends")
    public Object getFollows(@Param("id") String id) {
        User user = new User();
        user.setUserid(id);
        ResultEntity e = userService.getFriends(user);
        return e.getObject();
    }

    @UserLog("UserController")
    @GetMapping(value = "/api/getFollowSinger")
    public Object getFollowSinger(@Param("id") String id) {
        User user = new User();
        user.setUserid(id);
        return singerService.getSingerUserLike(user.getUserid());
    }

    @UserLog("UserController")
    @GetMapping(value = "/api/getSongList")
    public Object getSongList(@Param("id") String id) {
        User user = new User();
        user.setUserid(id);
        return userService.getSongLists(user).getObject();
    }

    @UserLog("UserController")
    @GetMapping(value="/api/setVIP")
    public Object setVIP(@Param("id") String id){
        return userService.setVIP(id).getObject();
    }

    @UserLog("UserController")
    @GetMapping(value="/api/buyMusic")
    public Object buyMusic(@Param("id") String id,@Param("mid") String mid,@Param("type") String type){
        return userService.buyMusic(id,mid,type).getObject();
    }

    @UserLog("UserController")
    @GetMapping(value="/api/charge")
    public Object charge(@Param("id") String id,@Param("money") String money){
        return userService.charge(id,money).getObject();
    }

    @UserLog("UserController")
    @PostMapping(value ="/api/Login")
    public ResultEntity Login(@RequestParam("id") String id, @RequestParam("pwd")String pwd, HttpServletRequest request){
        User user = new User();
        user.setUserid(id);
        user.setUserpassword(pwd);
        ResultEntity result = userService.SignIn(user);
        if(result.getSuccess()){
            request.getSession().setAttribute("user",result.getObject());
            request.getSession().setAttribute("visted",result.getObject());
        }
        return result;
    }

    @UserLog("UserController")
    @GetMapping(value = "/profile/showFans")
    public ModelAndView showFans(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession(false).getAttribute("visted");
        User my =(User) request.getSession(false).getAttribute("user");
        Map<String,Object> map ;
        ResultEntity e = userService.getFans(user);
        ArrayList<User> Follows = (ArrayList<User>)e.getObject();
        //"Follows"关注的用户 "FollowNum"关注的人数 "isFollow"是否关注
        map = showUtil.showFollow(my.getUserid(),Follows);
        return new ModelAndView("temp/fans_main",map);
    }

    @UserLog("UserController")
    @GetMapping(value="/api/showFollowUser")
    public Object showFollowUser(@Param("id") String id){
        User u = new User();
        u.setUserid(id);
        ResultEntity e =userService.getFriends(u);
        return e.getObject();
    }

    @UserLog("UserController")
    @RequestMapping(value = "/profile/changeFollow", method = RequestMethod.GET)
    public Map<String, String> changeFollow(HttpServletRequest request){
        User my =(User) request.getSession(false).getAttribute("user");
        String id = request.getParameter("id");
        return showUtil.changeFollow(my.getUserid(),id);
    }

    @UserLog("UserController")
    @GetMapping(value = "/api/isFollowed")
    public Object isFollowed(@Param("uid") String uid,@Param("fid") String fid){
        ResultEntity e=userService.isFriendExist(uid, fid);
        return e.getObject();
    }

    @UserLog("UserController")
    @GetMapping(value = "/api/changeFollow")
    public String changeFollow(@Param("uid") String uid,@Param("fid") String fid){
        ResultEntity e=userService.isFriendExist(uid, fid);
        if ((Boolean)e.getObject()==true){
            ResultEntity unFollow=userService.unFollowUser(uid, fid);
            return (Boolean)unFollow.getObject()==true?"取关成功":"取关失败";
        }
        else{
            ResultEntity follow=userService.followUser(uid, fid);
            return (Boolean)follow.getObject()==true?"关注成功":"关注失败";
        }
    }

    @UserLog("UserController")
    @GetMapping(value="/api/isFollowedSinger")
    public Object isFollowedSinger(@Param("uid") String uid,@Param("sid") String sid){
        ArrayList<Singer> s=singerService.getSingerUserLike(uid);
        for (int i=0;i<s.size();i++){
            if(s.get(i).getSingerid().equals(sid)){
                return true;
            }
        }
        return false;
    }

    @UserLog("UserController")
    @GetMapping(value="/api/changeFollowSinger")
    public Object changeFollowSinger(@Param("uid") String uid,@Param("sid") String sid){
        Boolean isFollowed=false;
        ArrayList<Singer> s=singerService.getSingerUserLike(uid);
        for (int i=0;i<s.size();i++){
            if(s.get(i).getSingerid().equals(sid)){
                isFollowed=true;
            }
        }
        if(isFollowed==false){
            return (Boolean)singerService.followSinger(uid, sid)==true?"关注成功":"关注失败";
        }
        else{
            return (Boolean)singerService.unfollowSinger(uid, sid)==true?"取关成功":"取关失败";
        }
    }

    @UserLog("UserController")
    @GetMapping(value="/api/isUserBanned")
    public Object isUserBanned(@Param("uid") String uid){
        return userService.isUserBanned(uid);
    }
}