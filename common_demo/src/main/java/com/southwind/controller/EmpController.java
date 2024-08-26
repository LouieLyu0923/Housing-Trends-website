package com.southwind.controller;

import com.google.gson.Gson;
import com.southwind.entity.Emp;
import com.southwind.entity.SysWeather;
import com.southwind.service.EmpService;
import com.southwind.vo.EmpDept;
import com.southwind.vo.EmpVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/getMsg")
    public String getMsg(@Param("id") Integer id){
        Emp emp=empService.findById(id);
        return emp.getName();
    }

    @GetMapping("/getId")
    public Integer getId(@Param("Name") String Name){
        Emp emp=empService.findByName(Name);
        return emp.getId();
    }

    @GetMapping("/getOne")
    public Emp getAll(@Param("id") Integer id){
        Emp emp=empService.findById(id);
        return emp;
    }

    @GetMapping("/getJson")
    public String getJson(@Param("id") Integer id){
        Emp emp=empService.findById(id);
        Gson gson =new Gson();
        return  gson.toJson(emp);
    }

    @GetMapping("/getAll")
    public Map<String,List> getAll(){
        List<Emp> list = this.empService.findEmpAll();
        List<String> nameList = new ArrayList<>();
        List<EmpVo> voList = new ArrayList<>();
        EmpVo vo;
        for (Emp emp : list) {
            vo=new EmpVo();
            vo.setName(emp.getName());
            vo.setValue(emp.getAge());
            voList.add(vo);
            nameList.add(emp.getName());
        }
        Map<String,List> map = new HashMap<>();
        map.put("name", nameList);
        map.put("value", voList);
//        System.out.println(map.getClass().toString());
//        System.out.println((map.get("name")).getClass().toString());
        return map;
    }

    @GetMapping("/getAllJson")
    public String getAllJson(){
        List<Emp> empAll = empService.findEmpAll();
        Gson gson = new Gson();
        return gson.toJson(empAll);
    }
}
