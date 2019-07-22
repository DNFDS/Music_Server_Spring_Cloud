package com.example.demo.dao;

import com.example.demo.entity.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryMapper {
    Integer addUserHistory(@Param("userID")String userID, @Param("singerID")String singerID);
    List<History>getAllHistory();
    History getHistoryByUserAndSinger(@Param("userID") String userID,@Param("singerID") String singerID);
    Integer updateHistory(@Param("userID") String userID,@Param("singerID") String singerID);
}
