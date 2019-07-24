package com.example.demo.controller;


import com.example.demo.entity.UserLogEnity;
import com.example.demo.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/database/userlog")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    @PostMapping(value = "/addUserLog")
    public void addUserLog(@RequestParam UserLogEnity userLogEnity){
        userLogService.addUserLog(userLogEnity);
    }

}
