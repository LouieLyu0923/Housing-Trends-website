package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.entity.SysUser;
import com.southwind.mapper.SysUserMapper;
import com.southwind.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.util.MD5Util;
import com.southwind.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-08-08
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Override
    public PageUtil queryUser(String key,Integer page) {
        Page<SysUser> userPage = new Page<>(page,PageUtil.SIZE);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(key), "user_name", key)
                .or().like(StringUtils.isNotBlank(key), "nick_name",key);
        Page<SysUser> resultPage = this.userMapper.selectPage(userPage, queryWrapper);
        PageUtil pageUtil = new PageUtil();
        pageUtil.setData(resultPage.getRecords());
        pageUtil.setPage(page);
        pageUtil.setTotal(resultPage.getTotal());
        pageUtil.setPages(resultPage.getPages());
        return pageUtil;
    }

    @Override
    public List<SysUser> findAllUser(){return userMapper.findAllUser();}

    @Override
    public SysUser register(SysUser user) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(user.getUserName());
        sysUser.setPassword(MD5Util.getSaltMD5(user.getPassword()));
        sysUser.setNickName(user.getNickName());
        sysUser.setMobile(user.getMobile());
        sysUser.setIsDeleted(0);
        sysUser.setAccountType(1);
        sysUser.setAccountState(1);
        sysUser.setUserRole("ROLE_USER");
        sysUser.setUpdateUser("");
        sysUser.setCreateUser(user.getUserName());
        int insert=this.userMapper.insert(sysUser);
        if(insert!=1){log.info("添加用户失败");}
        return sysUser;
    }
}
