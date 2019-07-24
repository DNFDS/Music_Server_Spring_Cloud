package com.example.demo.dao;

import java.util.Map;

public interface UserLogMapper {

    /**
     * @param Map
     * @IN :"userlog"
     * @OUT: "result"> 1:true 0:false
     */
    void addUserLog(Map<String, String> Map);
}