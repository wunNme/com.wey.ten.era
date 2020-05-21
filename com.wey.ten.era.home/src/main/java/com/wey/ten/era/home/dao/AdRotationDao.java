package com.wey.ten.era.home.dao;

import java.util.List;

import com.wey.ten.era.home.entity.AdRotation;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Mapper
public interface AdRotationDao extends BaseMapper<AdRotation>
{
    List<AdRotation> selectByExample();
}
