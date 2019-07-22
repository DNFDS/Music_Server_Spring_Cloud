package com.example.standardconsumer.service.Impl;

import com.example.standardconsumer.domain.BuyKey;
import com.example.standardconsumer.domain.SongList;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.feignApi.SongListServiceApi;
import com.example.standardconsumer.feignApi.UserServiceApi;
import com.example.standardconsumer.service.SingerService;
import com.example.standardconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserServiceApi userServiceApi;

    @Autowired
    SongListServiceApi songListServiceApi;

    @Override
    public ResultEntity SignIn(User user) {
        int example = userServiceApi.isUserExist(user.getUserid(), user.getUserpassword());
        if (example == 0) {
            return new ResultEntity(false, "User Not Exist or PassWord not correct", null);
        }
        if (example == 1) {
            ArrayList<User> users = userServiceApi.getUserById(user.getUserid());
            return new ResultEntity(true, "", users.get(0));
        }
        //session*/
        return new ResultEntity(false, this.getClass().toString() + "error", example);
    }

    @Override
    public ResultEntity Register(User user) {
        int result = userServiceApi.addUser(user);
        if (result == 0) {
            return new ResultEntity(false, "DB_error", null);
        } else {
            ArrayList<User> users = userServiceApi.getUserById(user.getUserid());
            return new ResultEntity(true, "", users.get(0));
        }
    }

    @Override
    public ResultEntity getFans(User user) {
        ArrayList<User> fans = userServiceApi.getFansByUserId(user.getUserid());
        return new ResultEntity(true, "", fans);
    }

    @Override
    public ResultEntity getFriends(User user) {
        ArrayList<User> friends = userServiceApi.getFriendsByUserId(user.getUserid());
        return new ResultEntity(true, "", friends);
    }

    @Override
    public ResultEntity getFollowSingers(User user) {
        return new ResultEntity(true, "", "");
    }

    @Override
    public ResultEntity getSongLists(User user) {
        return songListServiceApi.getSongLists(user.getUserid());
    }

    @Override
    public ArrayList<SongList> getCreatedSongList(String userid) {
        return songListServiceApi.getSongListCreatedByUserId(userid);
    }

    @Override
    public ArrayList<SongList> getKeepedSongList(String userid) {
        return songListServiceApi.getSongListKeepedByUserId(userid);
    }

    @Override
    public ResultEntity getUserById(String id) {
        User user;
        ArrayList<User> users = userServiceApi.getUserById(id);
        if (users.size() == 0) {
            user = new User();
            return new ResultEntity(false, "", null);
        } else
            user = users.get(0);
        return new ResultEntity(true, "", user);
    }

    @Override
    public ResultEntity followUser(String userid, String friendid) {
        boolean result;
        String succ = userServiceApi.addFriend(userid, friendid);
        if (succ.equals("1"))
            result = true;
        else
            result = false;
        return new ResultEntity(true, "", result);
    }

    @Override
    public ResultEntity unFollowUser(String userid, String friendid) {
        boolean result;
        String succ = userServiceApi.deleteFriend(userid, friendid);
        if (succ.equals("1"))
            result = true;
        else
            result = false;
        return new ResultEntity(true, "", result);
    }

    @Override
    public ResultEntity isFriendExist(String userid, String friendid) {
        String is = userServiceApi.isFriendExist(userid, friendid);
        if (is.equals("1"))
            return new ResultEntity(true, "", true);
        return new ResultEntity(true, "", false);
    }

    public SongList getFavoritelist(String userid) {
        ArrayList<SongList> songLists = songListServiceApi.getSongListCreatedByUserId(userid);
        for (SongList songList : songLists) {
            if (songList.getSonglistname().equals("我喜欢"))
                return songList;
        }
        return null;
    }

    public ArrayList<User> getUserByNamePart(String username) {
        return userServiceApi.getUserByNamePart(username);
    }

    @Override
    //设置VIP（实现）
    public ResultEntity setVIP(String userid) {
        Map<String, Object> mapVIP = userServiceApi.setVIP(userid);
        return mapVIP.get("succ").toString().equals("1") ? new ResultEntity(true, "", true) : new ResultEntity(false, "", false);
    }

    @Override
    //购买音乐（实现）
    public ResultEntity buyMusic(String userid, String songid, String type) {
        ArrayList<BuyKey> b = userServiceApi.getBuyRecord(userid, songid);
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i).getUserid().equals(userid) && b.get(i).getMusicid().equals(songid)) {
                return new ResultEntity(false, "", "已购买过");
            }
        }
        Map<String, Object> mapMoney = new HashMap<>();
        mapMoney.put("op", 2);
        int money = type.equals("a") ? 15 : 2;
        String succ = userServiceApi.moneyChange(userid,money);
        if (succ.equals("0")) {
            return new ResultEntity(false, "", false);
        } else {
            return userServiceApi.addBuy(userid,songid);
        }
    }

    @Override
    //充值（实现）
    public ResultEntity charge(String userid, String money) {
        String succ = userServiceApi.moneyChange(userid,Integer.parseInt(money));
        return succ.equals("1") ? new ResultEntity(true, "", true) : new ResultEntity(false, "", false);
    }

    @Override
    //获取购买的歌曲（实现）
    public ResultEntity getBoughtSongs(String userid) {
        return userServiceApi.getBoughtSongs(userid);
    }

    @Override
    //获取购买的专辑（实现）
    public ResultEntity getBoughtAlbums(String userid) {
        return userServiceApi.getBoughtAlbums(userid);
    }

    @Override
    public Object isUserBanned(String userid) {
        return userServiceApi.isUserBanned(userid);
    }
}
