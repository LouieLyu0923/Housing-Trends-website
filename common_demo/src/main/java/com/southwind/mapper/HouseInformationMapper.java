package com.southwind.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.entity.HouseInformation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southwind.vo.HouseInformationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2023-05-25
 */
@Mapper
@Repository
public interface HouseInformationMapper extends BaseMapper<HouseInformation> {
    public List<HouseInformation> findAllHouse();
    public Page<HouseInformationVO> selectPage(@Param("key1")String key1, @Param("key2")String key2,@Param("key3")String key3,@Param("key4")String key4,@Param("key5")String key5,@Param("key6")String key6,@Param("key7")String key7,@Param("key8")String key8,@Param("key9")String key9,@Param("key10")String key10,Page<HouseInformationVO> page, QueryWrapper<HouseInformationVO> wrapper);
    //Param可以作为数据库的动态参数，必须在mapper层，只有mapper层和xml通信
    public List<HouseInformationVO> selectAllHouse(@Param("key1")String key1, @Param("key2")String key2,@Param("key3")String key3,@Param("key4")String key4,@Param("key5")String key5,@Param("key6")String key6,@Param("key7")String key7,@Param("key8")String key8,@Param("key9")String key9,@Param("key10")String key10);
}
