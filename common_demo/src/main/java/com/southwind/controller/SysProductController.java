package com.southwind.controller;


import com.southwind.entity.SysProduct;
import com.southwind.entity.SysWeather;
import com.southwind.service.SysProductService;
import com.southwind.vo.SysProductVO;
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
@RequestMapping("/product")
public class SysProductController {

    @Autowired
    private SysProductService productService;

    @GetMapping("/pie")
    public Map<String,List> pie(){
        List<SysProduct> list = this.productService.list();
        SysProductVO vo;
        List<SysProductVO> voList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        for (SysProduct sysProduct : list) {
            vo = new SysProductVO();
            vo.setName(sysProduct.getName());
            vo.setValue(sysProduct.getSale());
            voList.add(vo);
            titleList.add(sysProduct.getName());
        }
        Map<String,List> map = new HashMap<>();
        map.put("data", voList);
        map.put("title", titleList);
        return map;
    }
}

