package com.southwind.service.impl;

import com.southwind.entity.Dept;
import com.southwind.mapper.DeptMapper;
import com.southwind.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAll() {
        return deptMapper.getAll();
    }
}
