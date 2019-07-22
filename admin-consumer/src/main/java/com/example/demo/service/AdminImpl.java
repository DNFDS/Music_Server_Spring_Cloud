package com.example.demo.service;

import com.example.demo.feignApi.AdminServiceApi;
import com.example.demo.feignApi.RankServiceApi;
import com.example.demo.service.AdminService;
import com.example.demo.domain.Album;
import com.example.demo.domain.Singer;
import com.example.demo.domain.Song;
import com.example.demo.domain.comments;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminImpl implements AdminService {

    @Autowired
    AdminServiceApi adminServiceApi;

    @Autowired
    RankServiceApi rankServiceApi;

    @Override
    public String isAdminExist(String id, String pwd){
        return adminServiceApi.isAdminExist(id,pwd);
    }

    @Override
    public String getMaxSongid(){
        return adminServiceApi.getMaxSongid();
    }

    @Override
    public String getMaxAlbumid(){
        return adminServiceApi.getMaxAlbumid();
    }

    @Override
    public String getMaxSingerid(){
        return adminServiceApi.getMaxSingerid();
    }

    @Override
    public Singer getSingerByName(String name){
        return adminServiceApi.getSingerByName(name);
    }

    @Override
    public String getAlbumidBySAName(String singerName, String albumName){
        return adminServiceApi.getAlbumidBySAName(singerName,albumName);
    }
    /*
    call ADMIN_PKG.add_album(#{albumid,mode=IN,jdbcType=VARCHAR},#{albumname,mode=IN,jdbcType=VARCHAR},
    #{albumage,mode=IN,jdbcType=VARCHAR},#{language,mode=IN,jdbcType=VARCHAR},
    #{company,mode=IN,jdbcType=VARCHAR},#{adminid,mode=IN,jdbcType=VARCHAR},
    #{singerid,mode=IN,jdbcType=VARCHAR},#{succ,mode=OUT,jdbcType=VARCHAR})\
    call ADMIN_PKG.add_singer(#{singerid,mode=IN,jdbcType=VARCHAR},#{singername,mode=IN,jdbcType=VARCHAR},
    #{region,mode=IN,jdbcType=VARCHAR},#{sex,mode=IN,jdbcType=VARCHAR},#{intro,mode=IN,jdbcType=VARCHAR},
    #{adminid,mode=IN,jdbcType=VARCHAR},#{succ,mode=OUT,jdbcType=VARCHAR})*/
    @Override
    public String addSong(Song s){
        return adminServiceApi.addSong(s);
    }

    @Override
    public String addAlbum(Album a){
        return adminServiceApi.addAlbum(a);
    }

    @Override
    public String addSinger(Singer s){
        return adminServiceApi.addSinger(s);
    }

    @Override
    public Song getDeleteSong(String singername, String albumname, String songname){
        return adminServiceApi.getDeleteSong(singername,albumname,songname);
    }

    @Override
    public Album getDeleteAlbum(String singername, String albumname){
        return adminServiceApi.getDeleteAlbum(singername,albumname);
    }

    @Override
    public Singer getDeleteSinger(String singername){
        return adminServiceApi.getDeleteSinger(singername);
    }

    @Override
	public Object deleteSong(String songid){
        return adminServiceApi.deleteSong(songid);
    }

    @Override
	public Object deleteAlbum(String albumid){
        return adminServiceApi.deleteAlbum(albumid);
    }

    @Override
	public Object deleteSinger(String singerid){
        return adminServiceApi.deleteAlbum(singerid);
    }

    @Override
    public String banUser(String adminid){
        return adminServiceApi.banUser(adminid);
    }

    @Override
    public ArrayList<comments> getNewComments(int pgnum){
        return adminServiceApi.getNewComments(pgnum);
    }

    @Override
    public int getNewCommentsTotal(){
        return adminServiceApi.getNewCommentsTotal();
    }
    
    @Override
    public Object passComment(String uid, String sid, String ctime){
        return adminServiceApi.passComment(uid,sid,ctime);
    }

    @Override
	public Object failComment(String uid, String sid, String ctime){
        return adminServiceApi.failComment(uid,sid,ctime);
    }

    @Override
    public int getBanTotal(){
        return adminServiceApi.getBanTotal();
    }

    @Override
    public Object getBan(int pgnum){
        return adminServiceApi.getBan(pgnum);
    }

    @Override
    public Object unBan(String uid){
        return adminServiceApi.unBan(uid);
    }

    @Override
    public String[] getBasicInfo(String aid){
        return adminServiceApi.getBasicInfo(aid);
    }

    @Override
    public int getSingerTotal(){
        return adminServiceApi.getSingerTotal();
    }

    @Override
	public int getAlbumTotal(){
        return adminServiceApi.getAlbumTotal();
    }

    @Override
	public int getSongTotal(){
        return adminServiceApi.getSongTotal();
    }

    @Override
	public ArrayList<Singer> getSingers(int pgnum){
        return adminServiceApi.getSingers(pgnum);
    }

    @Override
	public ArrayList<Album> getAlbums(int pgnum){
        return adminServiceApi.getAlbums(pgnum);
    }

    @Override
	public ArrayList<Song> getSongs(int pgnum){
        return adminServiceApi.getSongs(pgnum);
    }

    @Override
    public ArrayList<Singer> fuzzySingers(String singername){
        return adminServiceApi.fuzzySingers(singername);
    }

    @Override
    public String changeSingerImg(String singerid,String url){
        return adminServiceApi.changeSingerImg(singerid,url);
    }

    @Override
    public ArrayList<Album> fuzzyAlbums(String albumname){
        return adminServiceApi.fuzzyAlbums(albumname);
    }

    @Override
    public ArrayList<Song> fuzzySongs(String songname){
        return adminServiceApi.fuzzySongs(songname);
    }

    @Override
    public ArrayList<String> getAlbumBySingerName(String singername){
        return adminServiceApi.getAlbumBySingerName(singername);
    }
    
    @Override
	public ArrayList<String> getSongBySAName(String singername,String albumname){
        return adminServiceApi.getSongBySAName(singername,albumname);
    }

    @Override
    public String updateRankCache() {
        return rankServiceApi.updateRankCache();
    }

}
