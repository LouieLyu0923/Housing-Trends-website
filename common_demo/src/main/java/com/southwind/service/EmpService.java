package com.southwind.service;

import com.southwind.entity.Emp;
import com.southwind.vo.EmpDept;

import java.util.List;

public interface EmpService {
    public Emp findById(Integer id);
    public Emp findByName(String name);
    public List<Emp> findEmpAll();
}
