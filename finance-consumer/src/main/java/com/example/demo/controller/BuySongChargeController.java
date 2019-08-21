package com.example.demo.controller;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.standardconsumer.service.AlbumService;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/finance-consumer")
public class BuyChargeController {

    @Autowired
    BuyChargeService buyChargeService;

    @UserLog("BuyChargeController")
    @RequestMapping(value ="/api/buysong",method = RequestMethod.GET)
    public Object buySong(@Param("userid") String userid,@Param("songid") String songid){
        return buyChargeService.buySong(userid,songid);
    }
}