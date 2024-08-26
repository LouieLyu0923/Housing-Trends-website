package com.southwind.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southwind.entity.Dept;
import com.southwind.entity.Emp;
import com.southwind.vo.EmpDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface EmpMapper extends BaseMapper<Emp> {
    public Emp findById(Integer id);
    public Emp findByName(String Name);
    public List<Emp> findEmpAll();
}
