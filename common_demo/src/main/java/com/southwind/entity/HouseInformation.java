package com.southwind.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2023-05-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "house_information")
  @EqualsAndHashCode(callSuper = false)
    public class HouseInformation implements Serializable {

    private static final long serialVersionUID=1L;

  @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String houseName;

    private Float houseArea;

    private Float price;

    private String houseAspect;

    private String floor;
    
    private Integer add_house_id;
    
    private Integer date_house_id;
    
    private Integer type_house_id;

}
