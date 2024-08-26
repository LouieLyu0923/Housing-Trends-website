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
 * @since 2023-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "house_advantage")
  @EqualsAndHashCode(callSuper = false)
    public class HouseAdvantage implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer adId;

    private Integer advanId;
}
