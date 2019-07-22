package com.example.demo.redis;

import com.example.demo.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RankCache {

    @Autowired
    RedisUtil redisUtil;
    /**
     * 得到单曲排行榜缓存
     */
    public List<Object> getSongRankCache() {
        List<Object>  song_rank=redisUtil.lGet(   "SongRank",0,-1);
        return song_rank;
    }
    /**
     * 得到单曲排行榜缓存
     */
    public List<Object> getSingerRankCache() {
        List<Object>  singer_rank=redisUtil.lGet(   "SingerRank",0,-1);
        return singer_rank;
    }
    /**
     * 得到单曲排行榜缓存
     */
    public List<Object> getAlbumRankCache() {
        List<Object>  album_rank=redisUtil.lGet(   "AlbumRank",0,-1);
        return album_rank;
    }

    /**
     * 清空缓存内容
     */
    public void clearAll() {
        redisUtil.del( "SongRank","SingerRank","AlbumRank");
    }

    /**
     *  载入新的缓存
     */
    public void putCache(List<Object> song_list,List<Object> singer_list,List<Object> album_list) {
        redisUtil.lSet( "SongRank", song_list);
        redisUtil.lSet( "SingerRank", singer_list);
        redisUtil.lSet( "AlbumRank", album_list);
    }
}
