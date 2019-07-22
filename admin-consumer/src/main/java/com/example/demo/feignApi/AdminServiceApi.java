package com.example.demo.feignApi;

import com.example.demo.domain.Album;
import com.example.demo.domain.Singer;
import com.example.demo.domain.Song;
import com.example.demo.domain.comments;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Component
@FeignClient("database-providr")
@RequestMapping("/database/admin")
public interface AdminServiceApi {

	@GetMapping(value = "/isAdminExist")
	String isAdminExist(@RequestParam String id,@RequestParam String pwd);

	@GetMapping(value = "/getMaxSongid")
	String getMaxSongid();

	@GetMapping(value = "/getMaxAlbumid")
	String getMaxAlbumid();

	@GetMapping(value = "/getMaxSingerid")
	String getMaxSingerid();

	@GetMapping(value = "/getSingerByName")
	Singer getSingerByName(@RequestParam String name);

	@GetMapping(value = "/getAlbumidBySAName")
	String getAlbumidBySAName(@RequestParam String singerName,@RequestParam  String albumName);

	@PostMapping(value = "/addSong")
	String addSong(@RequestParam Song s);

	@PostMapping(value = "/addAlbum")
	String addAlbum(@RequestParam Album a);

	@PostMapping(value = "/addSinger")
	String addSinger(@RequestParam Singer s);

	@GetMapping(value = "/getDeleteSong")
	Song getDeleteSong(@RequestParam String singername, @RequestParam String albumname,@RequestParam  String songname);

	@GetMapping(value = "/getDeleteAlbum")
	Album getDeleteAlbum(@RequestParam String singername,@RequestParam  String albumname);

	@GetMapping(value = "/getDeleteSinger")
	Singer getDeleteSinger(@RequestParam String singername);

	@GetMapping(value = "/deleteSong")
	Object deleteSong(@RequestParam String songid);

	@GetMapping(value = "/deleteAlbum")
	Object deleteAlbum(@RequestParam String albumid);

	@GetMapping(value = "/deleteSinger")
	Object deleteSinger(@RequestParam String singerid);

	@GetMapping(value = "/banUser")
	String banUser(@RequestParam String adminid);

	@GetMapping(value = "/getNewComments")
	ArrayList<comments> getNewComments(@RequestParam int pgnum);

	@GetMapping(value = "/getNewCommentsTotal")
	int getNewCommentsTotal();

	@GetMapping(value = "/passComment")
	Object passComment(@RequestParam String uid,@RequestParam  String sid,@RequestParam  String ctime);

	@GetMapping(value = "/failComment")
	Object failComment(@RequestParam String uid,@RequestParam  String sid,@RequestParam  String ctime);

	@GetMapping(value = "/getBanTotal")
	int getBanTotal();

	@GetMapping(value = "/getBan")
	Object getBan(@RequestParam int pgnum);

	@GetMapping(value = "/unBan")
	Object unBan(@RequestParam String uid);

	@GetMapping(value = "/getBasicInfo")
	String[] getBasicInfo(@RequestParam String aid);

	@GetMapping(value = "/getSingerTotal")
	int getSingerTotal();

	@GetMapping(value = "/getAlbumTotal")
	int getAlbumTotal();

	@GetMapping(value = "/getSongTotal")
	int getSongTotal();

	@GetMapping(value = "/getSingers")
	ArrayList<Singer> getSingers(@RequestParam int pgnum);

	@GetMapping(value = "/getAlbums")
	ArrayList<Album> getAlbums(@RequestParam int pgnum);

	@GetMapping(value = "/getSongs")
	ArrayList<Song> getSongs(@RequestParam int pgnum);

	@GetMapping(value = "/fuzzySingers")
	ArrayList<Singer> fuzzySingers(@RequestParam String singername);

	@GetMapping(value = "/changeSingerImg")
	String changeSingerImg(@RequestParam String singerid,@RequestParam  String url);

	@GetMapping(value = "/fuzzyAlbums")
	ArrayList<Album> fuzzyAlbums(@RequestParam String albumname);

	@GetMapping(value = "/fuzzySongs")
	ArrayList<Song> fuzzySongs(@RequestParam String songname);

	@GetMapping(value = "/getAlbumBySingerName")
	ArrayList<String> getAlbumBySingerName(@RequestParam String singername);

	@GetMapping(value = "/getSongBySAName")
	ArrayList<String> getSongBySAName(@RequestParam String singername,@RequestParam  String albumname);

}

