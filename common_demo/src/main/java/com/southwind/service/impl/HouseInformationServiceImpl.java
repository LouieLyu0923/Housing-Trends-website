package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.entity.Address;
import com.southwind.entity.HouseInformation;
import com.southwind.entity.HouseType;
import com.southwind.entity.SysUser;
import com.southwind.mapper.HouseInformationMapper;
import com.southwind.mapper.SysUserMapper;
import com.southwind.service.HouseInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.util.PageUtil;
import com.southwind.vo.HouseInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-05-25
 */
@Service
public class HouseInformationServiceImpl extends ServiceImpl<HouseInformationMapper, HouseInformation> implements HouseInformationService {

    @Autowired
    private HouseInformationMapper houseInformationMapper;

    @Override
    public List<HouseInformation> findAllHouse() {
        return houseInformationMapper.findAllHouse();
    }

    @Override
    public List<HouseInformationVO> queryAllHouse(String key1, String key2, String key3, String key4,String key5,String key6,String key7,String key8,String key9,String key10){
        List<HouseInformationVO> intersectedRecords = this.houseInformationMapper.selectAllHouse(key1,key2,key3,key4,key5,key6,key7,key8,key9,key10);
        return intersectedRecords;
    }


    @Override
    public IPage<HouseInformationVO> queryHouse(String key1, String key2, String key3, String key4,String key5,String key6,String key7,String key8,String key9,String key10,Integer page) {
        Page<HouseInformationVO> userPage = new Page<>(page,PageUtil.SIZE);
        QueryWrapper<HouseInformationVO> queryWrapper = new QueryWrapper<>();



        Page<HouseInformationVO> resultPage1 = this.houseInformationMapper.selectPage(key1,key2,key3,key4,key5,key6,key7,key8,key9,key10,userPage, queryWrapper);
        List<HouseInformationVO> record1 = resultPage1.getRecords();


        Page<HouseInformationVO> intersectedPage = new Page<>(resultPage1.getCurrent(), resultPage1.getSize(), resultPage1.getTotal());
        intersectedPage.setRecords(record1);




        System.out.println("总页数 = "+ intersectedPage.getPages());
        return intersectedPage;
    }
}
