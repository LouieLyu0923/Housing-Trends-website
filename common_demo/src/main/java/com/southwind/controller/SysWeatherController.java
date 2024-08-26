package com.southwind.controller;


import com.southwind.entity.SysWeather;
import com.southwind.service.SysWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/weather")
public class SysWeatherController {

    @Autowired
    private SysWeatherService weatherService;

    @GetMapping("/line")
    public Map<String, List> lineData(){
        List<SysWeather> list = this.weatherService.list();
        List<String> dateList = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();
        List<Integer> minList = new ArrayList<>();
        for (SysWeather sysWeather : list) {
            dateList.add(sysWeather.getDate());
            maxList.add(sysWeather.getMaxTemperature());
            minList.add(sysWeather.getMinTemperature());
        }
        Map<String,List> map = new HashMap<>();
        map.put("date", dateList);
        map.put("max", maxList);
        map.put("min", minList);
        return map;
    }
}

