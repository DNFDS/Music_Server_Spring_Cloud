package com.example.standardconsumer.api;

import com.example.standardconsumer.common.ServerException;
import com.example.standardconsumer.service.AlbumService;
import com.example.standardconsumer.service.SingerService;
import com.example.standardconsumer.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@CrossOrigin
@RequestMapping("/standard-consumer")
public class RankController
{
    private static Logger log = LoggerFactory.getLogger(RankController.class);
    @Autowired
    private SongService songService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private AlbumService albumService;
    /**
     * 得到song_rank的缓存
     * @return
     */
    @GetMapping(value = "/getSongRankList")
    public List<Object> getSongRank() {
        try {
            return songService.getSongRankList();
        } catch (Exception e) {
            log.error("get song rank error for : ", e);
            throw  new ServerException(e);
        }
    }

    /**
     * 得到singer_rank的缓存
     * @return
     */
    @GetMapping(value = "/getSingerRankList")
    public List<Object> getSingerRankList() {
        try {
            return singerService.getSingerRankList();
        } catch (Exception e) {
            log.error("get singer rank error for : ", e);
            throw  new ServerException(e);
        }
    }

    /**
     * 得到album_rank的缓存
     * @return
     */
    @GetMapping(value = "/getAlbumRankList")
    public List<Object> getAlbumRankList() {
        try {
            return albumService.getAlbumRankList();
        } catch (Exception e) {
            log.error("get album rank cache error for : ", e);
            throw  new ServerException(e);
        }
    }

}
