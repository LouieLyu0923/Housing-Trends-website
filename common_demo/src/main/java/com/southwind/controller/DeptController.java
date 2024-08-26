package com.southwind.controller;

import com.southwind.entity.Dept;
import com.southwind.entity.Emp;
import com.southwind.service.DeptService;
import com.southwind.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/all")
    public List<Dept> getAll(){
        return deptService.getAll();
    }
}