package com.example.standardconsumer.feignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("cms-consumer")
@RequestMapping("/cms/cache")
public interface RankServiceApi {
    @GetMapping(value = "/getSongRankCache")
    List<Object> getSongRankCache();

    @GetMapping(value = "/getSingerRankCache")
    List<Object> getSingerRankCache();

    @GetMapping(value = "/getAlbumRankCache")
    List<Object> getAlbumRankCache();
}
