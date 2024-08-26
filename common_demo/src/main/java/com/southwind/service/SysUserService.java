package com.southwind.service;

import com.southwind.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.util.PageUtil;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-08-08
 */
public interface SysUserService extends IService<SysUser> {
    public List<SysUser> findAllUser();
    public PageUtil queryUser(String key,Integer page);
    public SysUser register(SysUser user);
}
