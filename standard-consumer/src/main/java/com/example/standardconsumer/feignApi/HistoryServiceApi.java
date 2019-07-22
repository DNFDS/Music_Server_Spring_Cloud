package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.History;
import com.example.standardconsumer.domain.Song;
import com.example.standardconsumer.domain.SongList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient("database-providr")
@RequestMapping("/database/history")
public interface HistoryServiceApi
{
    @GetMapping(value = "/getHistoryByUserAndSinger")
    History getHistoryByUserAndSinger(@RequestParam String userID, @RequestParam String singerID);

    @PostMapping(value = "/addUserHistory")
    Integer addUserHistory(@RequestParam String userID,@RequestParam String singerID);

    @PostMapping(value = "/updateHistory")
    Integer updateHistory(@RequestParam String userID,@RequestParam String singerID);

    @GetMapping(value = "/getAllHistory")
    List<History> getAllHistory();

}
