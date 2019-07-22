package com.example.standardconsumer.feignApi;

import com.example.standardconsumer.domain.Album;
import com.example.standardconsumer.domain.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@FeignClient("database-providr")
@RequestMapping("/database/album")
public interface AlbumServiceApi {

    @GetMapping(value = "/getCommandAlbum")
    public ArrayList<Album> getCommandAlbum(@RequestParam String userid);

    @GetMapping(value = "/getAlbumByAlbumId")
    public ArrayList<Album> getAlbumByAlbumId(@RequestParam String albumid);

    @GetMapping(value = "/getAlbumByNamePart")
    public ArrayList<Album> getAlbumByNamePart(@RequestParam String name);

    @GetMapping(value = "/getSongsInAlbum")
    public ArrayList<Song> getSongsInAlbum(@RequestParam String albumid);

    @PostMapping(value = "/addAlbum")
    public Integer addAlbum(@RequestBody Album album);

    @GetMapping(value = "/getAlbumBySingerId")
    public ArrayList<Album> getAlbumBySingerId(@RequestParam String singerid);
}
