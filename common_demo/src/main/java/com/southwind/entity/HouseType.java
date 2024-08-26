package com.southwind.entity;

import java.io.Serializable;

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
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "house_type")
  @EqualsAndHashCode(callSuper = false)
    public class HouseType implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer typeId;

    private String type;


}
