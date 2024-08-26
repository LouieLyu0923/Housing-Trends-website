package com.southwind.mapper;

import com.southwind.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-08-08
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
public List<SysUser> findAllUser();

}
