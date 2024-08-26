package com.southwind.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2023-05-20
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Map implements Serializable {

    private static final long serialVersionUID=1L;

    private String name;

    private Integer value;


}
