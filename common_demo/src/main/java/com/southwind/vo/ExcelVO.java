package com.southwind.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelVO {
    @ExcelProperty("商品编号")
    private Integer id;
    @ExcelProperty("商品名称")
    private String name;
    @ExcelProperty("商品销量")
    private Integer sale;
}
