package com.example.standardconsumer.api;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.domain.result.ResultEntity;
import com.example.standardconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/standard-consumer")
public class RegisterController {

    @Autowired
    private UserService userService;

    @UserLog("RegisterController")
    @RequestMapping(value ="/api/Register",method = RequestMethod.POST)
    public Map Register(@RequestParam("name") String name,@RequestParam("pwd")String pwd){
        Map<String, Object>map = new HashMap<>();
        if(name==null||pwd==null){
            map.put("success", false);
            return map;
        }
        User user = new User();
        user.setUsername(name);
        user.setUserpassword(pwd);
        ResultEntity result = userService.Register(user);
        if(result.getSuccess()){
            map.put("success", true);
            map.put("user", result.getObject());
        }
        else{
            map.put("success", false);
        }
        return map;
    }
}
