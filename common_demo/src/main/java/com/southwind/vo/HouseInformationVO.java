package com.southwind.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class HouseInformationVO {

    private Integer id;

    private String houseName;

    private Float houseArea;

    private Float price;

    private String houseAspect;

    private String floor;

    private String type;

    private String urbanArea;

    private String street;

    private String date;

    private String advantage;
}
