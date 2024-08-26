package com.southwind.entity;

import java.time.LocalDateTime;
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
 * @since 2023-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "house_date")
  @EqualsAndHashCode(callSuper = false)
    public class HouseDate implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer dateId;

    private String date;


}
