package com.example.demo.api;


import com.example.demo.common.ServerException;
import com.example.demo.common.constants.UserLog;
import com.example.demo.domain.Album;
import com.example.demo.domain.Singer;
import com.example.demo.domain.Song;
import com.example.demo.domain.comments;
import com.example.demo.service.AdminService;
import feign.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/admin-consumer")
public class AdminController {

    @Autowired
    private AdminService adminService;
    private static Logger log = LoggerFactory.getLogger(AdminController.class);

    @UserLog("AdminController")
    @PostMapping(value = "/api/adminLogin")
    public String adminLogin(@Param("id") String id, @Param("pwd") String pwd){
        return adminService.isAdminExist(id,pwd);
    }

    @UserLog("AdminController")
    @GetMapping(value = "/api/maxSongid")
    public String maxSongid(){
        return adminService.getMaxSongid();
    }

    @UserLog("AdminController")
    @GetMapping(value = "/api/maxAlbumid")
    public String maxAlbumid(){
        return adminService.getMaxAlbumid();
    }

    @UserLog("AdminController")
    @GetMapping(value = "/api/maxSingerid")
    public String maxSingerid(){
        return adminService.getMaxSingerid();
    }

    @UserLog("AdminController")
    @GetMapping(value = "/api/getSingeridByName")
    public String getSingeridByName(@Param("name") String name){
        Singer s=adminService.getSingerByName(name);
        return s.getSingerid();
    }

    @UserLog("AdminController")
    @GetMapping(value = "/api/getAlbumidBySAName")
    public String getAlbumidBySAName(@Param("sname") String sname,@Param("aname") String aname){
        return adminService.getAlbumidBySAName(sname,aname);
    }

    @UserLog("AdminController")
    @PostMapping(value = "/api/addSong")
    public String addSong(
            @Param("id") String id,
            @Param("name") String name,
            @Param("albumid") String albumid,
            @Param("length") String length,
            @Param("language") String language,
            @Param("school") String school,
            @Param("company") String company,
            @Param("age") String age,
            @Param("adminid") String adminid,
            @Param("free") String free,
            @Param("songpath") String songpath,
            @Param("songimg") String songimg,
            @Param("singername") String singername
    ){
        Song s=new Song();
        s.setAdminid(adminid);
        s.setAlbumid(albumid);
        s.setCompany(company);
        s.setLanguage(language);
        s.setLength(length);
        s.setSongage(age);
        s.setSongid(id);
        s.setSongschool(school);
        s.setSongname(name);
        s.setFree(free);
        s.setSongpath(songpath);
        s.setSongimage(songimg);
        s.setSinger(singername);
        return (String)adminService.addSong(s);
    }

    @UserLog("AdminController")
    @PostMapping(value = "/api/addAlbum")
    public String addAlbum(
            @Param("id") String id,
            @Param("name") String name,
            @Param("language") String language,
            @Param("school") String school,
            @Param("company") String company,
            @Param("age") String age,
            @Param("adminid") String adminid,
            @Param("singerid") String singerid,
            @Param("free") String free,
            @Param("albumimg") String albumimg
    ){
        Album a=new Album();
        a.setAdminid(adminid);
        a.setAlbumid(id);
        a.setCompany(company);
        a.setLanguage(language);
        a.setAlbumage(age);
        a.setSingerid(singerid);
        a.setAlbumname(name);
        a.setFree(free);
        a.setAlbumimage(albumimg);
        return (String)adminService.addAlbum(a);
    }

    @UserLog("AdminController")
    @PostMapping(value="/api/addSinger")
    public String addSinger(
            @Param("id") String id,
            @Param("name") String name,
            @Param("sex") String sex,
            @Param("region") String region,
            @Param("intro") String intro,
            @Param("adminid") String adminid,
            @Param("singerimg") String singerimg
    ){
        Singer s = new Singer();
        s.setSingerid(id);
        s.setSingername(name);
        s.setSingersex(sex);
        s.setRegion(region);
        s.setIntroduction(intro);
        s.setAdminid(adminid);
        s.setSingerimage(singerimg);
        return (String)adminService.addSinger(s);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/searchDeleteSong")
    public Song searchDeleteSong(@Param("singername") String singername,@Param("albumname") String albumname,
                                 @Param("songname") String songname){
        return (Song)adminService.getDeleteSong(singername,albumname,songname);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/searchDeleteAlbum")
    public Album searchDeleteAlbum(@Param("singername") String singername,@Param("albumname") String albumname){
        return (Album)adminService.getDeleteAlbum(singername,albumname);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/searchDeleteSinger")
    public Singer searchDeleteSinger(@Param("singername") String singername){
        return (Singer)adminService.getDeleteSinger(singername);
    }

    @UserLog("AdminController")
    @DeleteMapping(value="/api/deleteSong")
    public Object deleteSong(@Param("songid") String songid){
        return adminService.deleteSong(songid);
    }

    @UserLog("AdminController")
    @DeleteMapping(value="/api/deleteAlbum")
    public Object deleteAlbum(@Param("albumid") String albumid){
        return adminService.deleteAlbum(albumid);
    }

    @UserLog("AdminController")
    @DeleteMapping(value="/api/deleteSinger")
    public Object deleteSinger(@Param("singerid") String singerid){
        return adminService.deleteSinger(singerid);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/banUser")
    public String banUser(@Param("adminid") String adminid){
        return adminService.banUser(adminid);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getNewComments")
    public ArrayList<comments> getNewComments(@Param("pgnum") int pgnum){
        return adminService.getNewComments(pgnum);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getNewCommentsTotal")
    public int getNewCommentsTotal(){
        return adminService.getNewCommentsTotal();
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/passComment")
    public Object passComment(@Param("uid") String uid,@Param("sid") String sid,@Param("ctime") String ctime){
        return adminService.passComment(uid,sid,ctime);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/failComment")
    public Object failComment(@Param("uid") String uid,@Param("sid") String sid,@Param("ctime") String ctime){
        return adminService.failComment(uid,sid,ctime);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getBanTotal")
    public int getBanTotal(){
        return adminService.getBanTotal();
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getBan")
    public Object getBan(@Param("pgnum") int pgnum){
        return adminService.getBan(pgnum);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/unBan")
    public Object unBan(@Param("uid") String uid){
        return adminService.unBan(uid);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getBasicInfo")
    public String[] getBasicInfo(@Param("aid") String aid){
        return adminService.getBasicInfo(aid);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getSingerTotal")
    public int getSingerTotal(){
        return adminService.getSingerTotal();
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getAlbumTotal")
    public int getAlbumTotal(){
        return adminService.getAlbumTotal();
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getSongTotal")
    public int getSongTotal(){
        return adminService.getSongTotal();
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getSingers")
    public ArrayList<Singer> getSingers(@Param("pgnum") int pgnum){
        return adminService.getSingers(pgnum);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getAlbums")
    public ArrayList<Album> getAlbums(@Param("pgnum") int pgnum){
        return adminService.getAlbums(pgnum);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getSongs")
    public ArrayList<Song> getSongs(@Param("pgnum") int pgnum){
        return adminService.getSongs(pgnum);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/fuzzySingers")
    public ArrayList<Singer> fuzzySingers(@Param("singername") String singername){
        return adminService.fuzzySingers(singername);
    }

    @UserLog("AdminController")
    @PostMapping(value="/api/changeSingerImg")
    public String changeSingerImg(@Param("singerid") String singerid,@Param("url") String url){
        return adminService.changeSingerImg(singerid, url);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/fuzzyAlbums")
    public ArrayList<Album> fuzzyAlbums(@Param("albumname") String albumname){
        return adminService.fuzzyAlbums(albumname);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/fuzzySongs")
    public ArrayList<Song> fuzzySongs(@Param("songname") String songname){
        return adminService.fuzzySongs(songname);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getAlbumBySingerName")
    public Object getAlbumBySingerName(@Param("singername") String singername){
        return adminService.getAlbumBySingerName(singername);
    }

    @UserLog("AdminController")
    @GetMapping(value="/api/getSongBySAName")
    public Object getSongBySAName(@Param("singername") String singername,@Param("albumname") String albumname){
        return adminService.getSongBySAName(singername, albumname);
    }

    @UserLog("AdminController")
    @PutMapping(value = "/updateRankCache")
    public String updateRankCache() {
        try {
            return adminService.updateRankCache();
        } catch (Exception e) {
            log.error("update rank cache error for : ", e);
            throw  new ServerException(e);
        }
    }

}
