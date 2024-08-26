package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "dept")
public class Dept implements Serializable {
    Integer id;
    String name;
    @TableField(exist = false)
    List<Emp> list;
}
