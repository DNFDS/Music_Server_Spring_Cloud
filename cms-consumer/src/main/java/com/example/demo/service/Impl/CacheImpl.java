package com.example.demo.service.Impl;

import com.example.demo.api.CacheController;
import com.example.demo.domain.Album;
import com.example.demo.domain.Singer;
import com.example.demo.domain.Song;
import com.example.demo.feignApi.RankServiceApi;
import com.example.demo.redis.RankCache;
import com.example.demo.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheImpl implements CacheService {

    private static Logger log = LoggerFactory.getLogger(CacheController.class);
    @Autowired
    RankServiceApi rankServiceApi;
    @Autowired
    RankCache rankCache;
    @Override
     public String updateRankCache() {
        // 1.查询数据库中的最新数据并遍历放入map
        List<Object> song_list = rankServiceApi.getSongRankList();
        List<Object> singer_list = rankServiceApi.getSingerRankList();
        List<Object> album_list = rankServiceApi.getAlbumRankList();
        // 2.清空Redis缓存后放入新的缓存
        rankCache.clearAll();
        rankCache.putCache(song_list,singer_list,album_list);
        log.info("完成排行榜缓存rankCache刷新");
        return "ok";
     }
    @Override
    public List<Object> getSongRankCache() {
        return rankCache.getSongRankCache();
    }
    @Override
    public List<Object> getSingerRankCache() {
        return rankCache.getSingerRankCache();
    }
    @Override
    public List<Object> getAlbumRankCache() {
        return rankCache.getAlbumRankCache();
    }
}
