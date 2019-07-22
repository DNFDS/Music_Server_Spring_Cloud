package com.example.demo.feignApi;

import com.example.demo.domain.Album;
import com.example.demo.domain.Singer;
import com.example.demo.domain.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@FeignClient("database-providr")
@RequestMapping("/database/rank")
public interface RankServiceApi {

    @GetMapping(value = "/getSongRankList")
    List<Object> getSongRankList();

    @GetMapping(value = "/getSingerRankList")
    List<Object> getSingerRankList();

    @GetMapping(value = "/getAlbumRankList")
    List<Object> getAlbumRankList();
}
