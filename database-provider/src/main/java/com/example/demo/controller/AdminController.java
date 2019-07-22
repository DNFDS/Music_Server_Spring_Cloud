package com.example.demo.controller;


import com.example.demo.entity.Album;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.comments;
import com.example.demo.service.AdminService;
import com.example.demo.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/database/album")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/isAdminExist")
    String isAdminExist(@RequestParam String id,@RequestParam String pwd){
        return adminService.isAdminExist(id,pwd);
    }

    @GetMapping(value = "/getMaxSongid")
    String getMaxSongid(){
        return adminService.getMaxSongid();
    }

    @GetMapping(value = "/getMaxAlbumid")
    String getMaxAlbumid(){
        return adminService.getMaxAlbumid();
    }

    @GetMapping(value = "/getMaxSingerid")
    String getMaxSingerid(){
        return adminService.getMaxSingerid();
    }

    @GetMapping(value = "/getSingerByName")
    Singer getSingerByName(@RequestParam String name){
        return adminService.getSingerByName(name);
    }

    @GetMapping(value = "/getAlbumidBySAName")
    String getAlbumidBySAName(@RequestParam String singerName,@RequestParam  String albumName){
        return adminService.getAlbumidBySAName(singerName,albumName);
    }

    @PostMapping(value = "/addSong")
    String addSong(@RequestParam Song s){
        return adminService.addSong(s);
    }

    @PostMapping(value = "/addAlbum")
    String addAlbum(@RequestParam Album a){
        return adminService.addAlbum(a);
    }

    @PostMapping(value = "/addSinger")
    String addSinger(@RequestParam Singer s){
        return adminService.addSinger(s);
    }

    @GetMapping(value = "/getDeleteSong")
    Song getDeleteSong(@RequestParam String singername, @RequestParam String albumname,@RequestParam  String songname){
        return adminService.getDeleteSong(singername,albumname,songname);
    }

    @GetMapping(value = "/getDeleteAlbum")
    Album getDeleteAlbum(@RequestParam String singername,@RequestParam  String albumname){
        return adminService.getDeleteAlbum(singername,albumname);
    }

    @GetMapping(value = "/getDeleteSinger")
    Singer getDeleteSinger(@RequestParam String singername){
        return adminService.getDeleteSinger(singername);
    }

    @GetMapping(value = "/deleteSong")
    Object deleteSong(@RequestParam String songid){
        return adminService.deleteSong(songid);
    }

    @GetMapping(value = "/deleteAlbum")
    Object deleteAlbum(@RequestParam String albumid){
        return adminService.deleteAlbum(albumid);
    }

    @GetMapping(value = "/deleteSinger")
    Object deleteSinger(@RequestParam String singerid){
        return adminService.deleteAlbum(singerid);
    }

    @GetMapping(value = "/banUser")
    String banUser(@RequestParam String adminid){
        return adminService.banUser(adminid);
    }

    @GetMapping(value = "/getNewComments")
    ArrayList<comments> getNewComments(@RequestParam int pgnum){
        return adminService.getNewComments(pgnum);
    }

    @GetMapping(value = "/getNewCommentsTotal")
    int getNewCommentsTotal(){
        return adminService.getNewCommentsTotal();
    }

    @GetMapping(value = "/passComment")
    Object passComment(@RequestParam String uid,@RequestParam  String sid,@RequestParam  String ctime){
        return adminService.passComment(uid,sid,ctime);
    }

    @GetMapping(value = "/failComment")
    Object failComment(@RequestParam String uid,@RequestParam  String sid,@RequestParam  String ctime){
        return adminService.failComment(uid,sid,ctime);
    }

    @GetMapping(value = "/getBanTotal")
    int getBanTotal(){
        return adminService.getBanTotal();
    }

    @GetMapping(value = "/getBan")
    Object getBan(@RequestParam int pgnum){
        return adminService.getBan(pgnum);
    }

    @GetMapping(value = "/unBan")
    Object unBan(@RequestParam String uid){
        return adminService.unBan(uid);
    }

    @GetMapping(value = "/getBasicInfo")
    String[] getBasicInfo(@RequestParam String aid){
        return adminService.getBasicInfo(aid);
    }

    @GetMapping(value = "/getSingerTotal")
    int getSingerTotal(){
        return adminService.getSingerTotal();
    }

    @GetMapping(value = "/getAlbumTotal")
    int getAlbumTotal(){
        return adminService.getAlbumTotal();
    }

    @GetMapping(value = "/getSongTotal")
    int getSongTotal(){
        return adminService.getSongTotal();
    }

    @GetMapping(value = "/getSingers")
    ArrayList<Singer> getSingers(@RequestParam int pgnum){
        return adminService.getSingers(pgnum);
    }

    @GetMapping(value = "/getAlbums")
    ArrayList<Album> getAlbums(@RequestParam int pgnum){
        return adminService.getAlbums(pgnum);
    }

    @GetMapping(value = "/getSongs")
    ArrayList<Song> getSongs(@RequestParam int pgnum){
        return adminService.getSongs(pgnum);
    }


    @GetMapping(value = "/fuzzySingers")
    ArrayList<Singer> fuzzySingers(@RequestParam String singername){
        return adminService.fuzzySingers(singername);
    }

    @GetMapping(value = "/changeSingerImg")
    String changeSingerImg(@RequestParam String singerid,@RequestParam  String url){
        return adminService.changeSingerImg(singerid,url);
    }

    @GetMapping(value = "/fuzzyAlbums")
    ArrayList<Album> fuzzyAlbums(@RequestParam String albumname){
        return adminService.fuzzyAlbums(albumname);
    }

    @GetMapping(value = "/fuzzySongs")
    ArrayList<Song> fuzzySongs(@RequestParam String songname){
        return adminService.fuzzySongs(songname);
    }

    @GetMapping(value = "/getAlbumBySingerName")
    ArrayList<String> getAlbumBySingerName(@RequestParam String singername){
        return adminService.getAlbumBySingerName(singername);
    }

    @GetMapping(value = "/getSongBySAName")
    ArrayList<String> getSongBySAName(@RequestParam String singername,@RequestParam  String albumname){
        return adminService.getSongBySAName(singername,albumname);
    }
}
