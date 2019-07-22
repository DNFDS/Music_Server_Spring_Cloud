package com.example.standardconsumer.api;

import com.example.standardconsumer.dao.SongListMapper;
import com.example.standardconsumer.dao.SongMapper;
import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.service.AlbumService;
import com.example.standardconsumer.service.SongListService;
import com.example.standardconsumer.service.SongService;
import com.example.standardconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;
    @Autowired
    private SongListService songListService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping(value ="/",method = RequestMethod.GET)
    public String index(Map<String, Object> map){
        map.put("test1","id");
        return "Login";
    }

    @RequestMapping(value ="/Login",method = RequestMethod.POST)
    public String Login(@RequestParam("id") String id,@RequestParam("pwd")String pwd,
                        Map<String, Object> map,HttpServletRequest request){
        User user = new User();
        user.setUserid(id);
        user.setUserpassword(pwd);
        ResultEntity result = userService.SignIn(user);
        if(result.getSuccess()){
            request.getSession().setAttribute("user",result.getObject());
            request.getSession().setAttribute("visted",result.getObject());
            return "redirect:/findMusic";
        }
        else{
            map.put("errMsg",result.getErrorMsg());
            return "Login";
        }
    }

    @RequestMapping(value = "/myPage")
    public String toMypage(HttpServletRequest request){
        Object o = request.getSession().getAttribute("user");
        request.getSession().setAttribute("visted",o);
        return "redirect:/profile/like_song";
    }
    @RequestMapping(value ="/Exit")
    public String Exit(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
    @RequestMapping(value = "/findMusic")
    public String findMusic(HttpServletRequest request,Map<String, Object> map){
        User user = (User) request.getSession(false).getAttribute("user");
        ArrayList<Song> songs = songService.getCommandSong(user.getUserid());
        ArrayList<SongList> songLists = songListService.getCommandSongList(user.getUserid());
        ArrayList<Album> albums = albumService.getCommandAlbum(user.getUserid());
        if(songs.size()>5){
            map.put("songs",songs.subList(0,5));
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

        return "find_music";
    }
}
