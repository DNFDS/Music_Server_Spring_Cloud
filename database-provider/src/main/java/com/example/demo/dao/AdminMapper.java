package com.example.demo.dao;

import java.util.Map;

import com.example.demo.entity.Admin;

public interface AdminMapper {
    void isAdminExist(Map<String,Object> map);

    void addSong(Map<String,Object> map);
    void addAlbum(Map<String,Object> map);
    void addSinger(Map<String,Object> map);
    void deleteSong(Map<String,Object> map);
    void deleteAlbum(Map<String,Object> map);
    void deleteSinger(Map<String,Object> map);
    void getMaxSongid(Map<String,Object> map);
    void getMaxAlbumid(Map<String,Object> map);
    void getMaxSingerid(Map<String,Object> map);
    void getSongBySASName(Map<String,Object> map);
    void getAlbumidBySAName(Map<String,Object> map);
    void getAlbumBySAName(Map<String,Object> map);
    void banSingleUser(Map<String,Object> map);
    void unBanUser(Map<String,Object> map);
    void banUser(Map<String,Object> map);
    void viewAllNewComments(Map<String,Object> map);
    void getNewCommentsTotal(Map<String,Object> map);
    void passComment(Map<String,Object> map);
    void failComment(Map<String,Object> map);
    void getBanTotal(Map<String,Object> map);
    void getBan(Map<String,Object> map);
    void unBan(Map<String,Object> map);
    void getBasicInfo(Map<String,Object> map);

    void getSingerTotal(Map<String,Object> map);
    void getAlbumTotal(Map<String,Object> map);
    void getSongTotal(Map<String,Object> map);
    void getSingers(Map<String,Object> map);
    void getAlbums(Map<String,Object> map);
    void getSongs(Map<String,Object> map);
    void fuzzySingers(Map<String,Object> map);
    void changeSingerImg(Map<String,Object> map);
    void fuzzyAlbums(Map<String,Object> map);
    void fuzzySongs(Map<String,Object> map);
    void getSongBySAName(Map<String,Object> map);
    void getAlbumBySingerName(Map<String,Object> map);

}