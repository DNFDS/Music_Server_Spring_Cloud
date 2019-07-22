package com.example.demo.dao;

import java.util.Map;

public interface HashMapper {

    void insertHash(Map<String,Object> Map);

    void buildIndex();

    void getSongName(Map<String,Object> Map);

    void selectHash(Map<String,Object> Map);

    void insertSong(Map<String,Object> Map);

}