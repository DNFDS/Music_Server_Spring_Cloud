package com.example.demo.service;

import com.example.demo.domain.Album;
import com.example.demo.domain.Singer;
import com.example.demo.domain.Song;
import com.example.demo.domain.comments;

import java.util.ArrayList;

public interface AdminService {

	String isAdminExist(String id, String pwd);

    String getMaxSongid();
    
    String getMaxAlbumid();

	String getMaxSingerid();

	Singer getSingerByName(String name);

	String getAlbumidBySAName(String singerName, String albumName);

    String addSong(Song s);
    
    String addAlbum(Album a);

    String addSinger(Singer s);

	Song getDeleteSong(String singername, String albumname, String songname);

	Album getDeleteAlbum(String singername, String albumname);

	Singer getDeleteSinger(String singername);

	Object deleteSong(String songid);

	Object deleteAlbum(String albumid);

	Object deleteSinger(String singerid);

	String banUser(String adminid);

	ArrayList<comments> getNewComments(int pgnum);

	int getNewCommentsTotal();

	Object passComment(String uid, String sid, String ctime);

	Object failComment(String uid, String sid, String ctime);

	int getBanTotal();

	Object getBan(int pgnum);

	Object unBan(String uid);

	String[] getBasicInfo(String aid);

	int getSingerTotal();

	int getAlbumTotal();

	int getSongTotal();

	ArrayList<Singer> getSingers(int pgnum);

	ArrayList<Album> getAlbums(int pgnum);

	ArrayList<Song> getSongs(int pgnum);

	ArrayList<Singer> fuzzySingers(String singername);

	String changeSingerImg(String singerid, String url);

	ArrayList<Album> fuzzyAlbums(String albumname);

	ArrayList<Song> fuzzySongs(String songname);

	ArrayList<String> getAlbumBySingerName(String singername);

	ArrayList<String> getSongBySAName(String singername, String albumname);

}

