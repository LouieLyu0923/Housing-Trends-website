package com.southwind.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedirectController {

    @GetMapping("/{url}")
    public String url(@PathVariable("url") String url){
        return url;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon(){

    }
}
