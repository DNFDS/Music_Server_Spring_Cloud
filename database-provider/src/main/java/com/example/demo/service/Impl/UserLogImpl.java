package com.example.demo.service.Impl;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.entity.result.ResultEntity;
import com.example.demo.service.SingerService;
import com.example.demo.service.UserLogService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserLogImpl implements UserLogService {
    @Resource
    private UserLogMapper userLogMapper;

    @Override
    public void addUserLog(UserLogEnity userLogEnity){
        HashMap<String, String> addUserLogMap = new HashMap<>();
        addUserLogMap.put("type",userLogEnity.getType());
        addUserLogMap.put("classandmethod",userLogEnity.getClassAndMethod());
        addUserLogMap.put("userid",userLogEnity.getUserId());
        addUserLogMap.put("reqmsg",userLogEnity.getReqMsg());
        addUserLogMap.put("resmsg",userLogEnity.getResMsg());
        addUserLogMap.put("adddate",userLogEnity.getAddDate());
        addUserLogMap.put("addtime",userLogEnity.getAddTime());
        userLogMapper.addUserLog(addUserLogMap);
    }

}
