package com.southwind.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.southwind.entity.Emp;
import com.southwind.entity.HouseInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.util.PageUtil;
import com.southwind.vo.HouseInformationVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-05-25
 */
public interface HouseInformationService {
    public List<HouseInformation> findAllHouse();
    public IPage<HouseInformationVO> queryHouse(String key1, String key2, String key3, String key4,String key5,String key6,String key7,String key8,String key9,String key10, Integer page);
    public List<HouseInformationVO> queryAllHouse(String key1, String key2, String key3, String key4,String key5,String key6,String key7,String key8,String key9,String key10);
}
