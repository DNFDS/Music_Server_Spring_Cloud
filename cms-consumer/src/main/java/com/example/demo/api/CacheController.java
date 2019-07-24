package com.example.demo.api;


import com.example.demo.common.exception.ServerException;
import com.example.demo.service.CacheService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/cms/cache")
public class CacheController {

    private static Logger log = LoggerFactory.getLogger(CacheController.class);
    @Autowired
    CacheService cacheService;

    /**
     * 更新rank表的缓存
     * @return
     */
    @ApiOperation(value = "更新所有排行榜缓存", notes = "更新Redis中的排行榜缓存")
    @PutMapping(value = "/updateRankCache")
    public String updateRankCache() {
        try {
            return cacheService.updateRankCache();
        } catch (Exception e) {
            log.error("update rank cache error for : ", e);
            throw  new ServerException(e);
        }
    }

    /**
     * 得到song_rank的缓存
     * @return
     */
    @ApiOperation(value = "得到单曲排行榜缓存", notes = "得到Redis中的单曲排行榜缓存")
    @GetMapping(value = "/getSongRankCache")
    public List<Object> getSongRankCache() {
        try {
            return cacheService.getSongRankCache();
        } catch (Exception e) {
            log.error("get song rank cache error for : ", e);
            throw  new ServerException(e);
        }
    }

    /**
     * 得到singer_rank的缓存
     * @return
     */
    @ApiOperation(value = "得到歌手排行榜缓存", notes = "得到Redis中的歌手排行榜缓存")
    @GetMapping(value = "/getSingerRankCache")
    public List<Object> getSingerRankCache() {
        try {
            return cacheService.getSingerRankCache();
        } catch (Exception e) {
            log.error("get singer rank cache error for : ", e);
            throw  new ServerException(e);
        }
    }

    /**
     * 得到album_rank的缓存
     * @return
     */
    @ApiOperation(value = "得到单曲的排行榜缓存", notes = "得到Redis中的单曲排行榜缓存")
    @GetMapping(value = "/getAlbumRankCache")
    public List<Object> getAlbumRankCache() {
        try {
            return cacheService.getAlbumRankCache();
        } catch (Exception e) {
            log.error("get album rank cache error for : ", e);
            throw  new ServerException(e);
        }
    }
}
