package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.History;
import com.example.standardconsumer.domain.Singer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient("database-providr")
@RequestMapping("/database/singer")
public interface SingerServiceApi
{
    @GetMapping(value = "/getSingerById")
    ArrayList<Singer> getSingerById( @RequestParam String singerID);

    @PostMapping(value = "/addSinger")
    Integer addSinger(@RequestBody Singer singer);

    @GetMapping(value = "/getSingerByName_part")
    ArrayList<Singer> getSingerByName_part( @RequestParam String word);

    @GetMapping(value = "/getFansNum")
    int getFansNum( @RequestParam String singerid);

    @GetMapping(value = "/likeSinger")
    String likeSinger(@RequestParam String userid,@RequestParam String singerid);

    @GetMapping(value = "/likeSinger")
    String unlikeSinger(@RequestParam String userid,@RequestParam String singerid);

    @GetMapping(value = "/isUserLikeSinger")
    String isUserLikeSinger(@RequestParam String singerid,@RequestParam String userid);

    @GetMapping(value = "/getSongSinger")
    ArrayList<Singer> getSongSinger( @RequestParam String userid);

}
