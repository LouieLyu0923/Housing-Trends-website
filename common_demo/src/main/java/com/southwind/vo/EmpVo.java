package com.southwind.vo;

import lombok.Data;

@Data
//echarts对属性名称有要求，看例子是value必须是value
public class EmpVo {
    private String name;
    private int value;
}
