package com.example.demo.service;

import java.util.List;

public interface CacheService {

	String updateRankCache();

	List<Object> getSongRankCache();

	List<Object> getSingerRankCache();

	List<Object> getAlbumRankCache();
}

