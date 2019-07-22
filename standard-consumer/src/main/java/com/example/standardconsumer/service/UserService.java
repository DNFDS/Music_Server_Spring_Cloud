package com.example.standardconsumer.service;

import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;

import java.util.ArrayList;

public interface UserService {
    ResultEntity SignIn(User user);
    ResultEntity Register(User user);
    ResultEntity getFans(User user);
    ResultEntity followUser(String userid, String friendid);
    ResultEntity unFollowUser(String userid, String friendid);
    ResultEntity getFriends(User user);
    ResultEntity getFollowSingers(User user);
    ResultEntity getSongLists(User user);
    ArrayList<SongList> getCreatedSongList(String userid);
    ArrayList<SongList> getKeepedSongList(String userid);
    ArrayList<User> getUserByNamePart(String username);
    ResultEntity getUserById(String id);
    ResultEntity isFriendExist(String userid,String friendid);
    SongList getFavoritelist(String userid);
    ResultEntity setVIP(String userid);
    ResultEntity buyMusic(String userid,String musicid,String type);
    ResultEntity charge(String id, String money);
    ResultEntity getBoughtSongs(String id);
    ResultEntity getBoughtAlbums(String id);
    Object isUserBanned(String uid);
}
