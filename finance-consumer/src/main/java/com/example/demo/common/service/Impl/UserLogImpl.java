package com.example.demo.common.service.Impl;

import com.example.standardconsumer.common.domain.UserLogEnity;
import com.example.standardconsumer.common.feignApi.UserLogServiceApi;
import com.example.standardconsumer.common.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogImpl implements UserLogService {

    @Autowired
    UserLogServiceApi userLogServiceApi;

    @Override
    public void addUserLog(UserLogEnity userLogEnity){
        userLogServiceApi.addUserLog(userLogEnity);
    }

}
