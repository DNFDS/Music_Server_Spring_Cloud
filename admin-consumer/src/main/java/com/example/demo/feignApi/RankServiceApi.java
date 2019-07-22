package com.example.demo.feignApi;

import com.example.demo.domain.Album;
import com.example.demo.domain.Singer;
import com.example.demo.domain.Song;
import com.example.demo.domain.comments;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Component
@FeignClient("cms-consumer")
@RequestMapping("/cms/cache")
public interface RankServiceApi {

	@GetMapping(value = "/updateRankCache")
    String updateRankCache();
}

