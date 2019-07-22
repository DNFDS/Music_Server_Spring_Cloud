package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.*;
import com.example.standardconsumer.domain.result.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@FeignClient("database-providr")
@RequestMapping("/database/user")
public interface UserServiceApi
{
    @GetMapping(value = "/getUserById")
    ArrayList<User> getUserById(@RequestParam String userid);

    @GetMapping(value = "/isUserExist")
    int isUserExist(@RequestParam String userid,@RequestParam String userpassword);

    @PostMapping(value = "/addUser")
    int addUser(@RequestParam User user);

    @GetMapping(value = "/getFansByUserId")
    ArrayList<User> getFansByUserId(@RequestParam String userid);

    @GetMapping(value = "/getFriendsByUserId")
    ArrayList<User> getFriendsByUserId(@RequestParam String userid);

    @PostMapping(value = "/deleteFriend")
    String deleteFriend(@RequestParam String userid,@RequestParam String friendid);

    @PostMapping(value = "/addFriend")
    String addFriend(@RequestParam String userid,@RequestParam String friendid);

    @GetMapping(value = "/isFriendExist")
    String isFriendExist(@RequestParam String userid,@RequestParam String friendid);

    @GetMapping(value = "/getUserByNamePart")
    ArrayList<User>  getUserByNamePart(@RequestParam String username);

    @PostMapping(value = "/setVIP")
    Map<String,Object> setVIP(@RequestParam String userid);

    @GetMapping(value = "/getBuyRecord")
    ArrayList<BuyKey> getBuyRecord(@RequestParam String userid,@RequestParam String songid);

    @GetMapping(value = "/moneyChange")
    String moneyChange(@RequestParam String userid,@RequestParam int money);

    @PostMapping(value = "/moneyChange")
    ResultEntity addBuy(@RequestParam String userid, @RequestParam String songid);

    @PostMapping(value = "/getBoughtSongs")
    ResultEntity getBoughtSongs(@RequestParam String userid);

    @PostMapping(value = "/getBoughtAlbums")
    ResultEntity getBoughtAlbums(@RequestParam String userid);

    @PostMapping(value = "/isUserBanned")
    Object isUserBanned(@RequestParam String userid);
}
