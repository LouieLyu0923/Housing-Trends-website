package com.southwind.service.impl;

import com.southwind.entity.Emp;
import com.southwind.mapper.EmpMapper;
import com.southwind.service.EmpService;
import com.southwind.vo.EmpDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp findById(Integer id){
        return empMapper.findById(id);
    }

    @Override
    public Emp findByName(String Name){
        return empMapper.findByName(Name);
    }

    @Override
    public List<Emp> findEmpAll(){
        return empMapper.findEmpAll();
    }

}
