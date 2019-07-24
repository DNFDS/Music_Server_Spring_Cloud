package com.example.demo.common.feignApi;

import com.example.demo.common.domain.UserLogEnity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("database-providr")
@RequestMapping("/database/userlog")
public interface UserLogServiceApi {

    @PostMapping(value = "/addUserLog")
    public void addUserLog(@RequestParam UserLogEnity userLogEnity);
}
