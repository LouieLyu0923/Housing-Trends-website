package com.southwind.controller;


import com.southwind.service.HouseInformationService;
import com.southwind.service.impl.DataPreloadService;
import com.southwind.vo.HouseInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class SysIndexController {

    @Autowired
    private HouseInformationService service;
    @Autowired
    private DataPreloadService dataPreloadService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/index")
    public Model index(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("role", userDetails.getUsername());
        System.out.println(userDetails.getUsername() + " 用户登录成功");

        // Asynchronously preload map data
        dataPreloadService.preloadMapData();

        return model;
    }



}