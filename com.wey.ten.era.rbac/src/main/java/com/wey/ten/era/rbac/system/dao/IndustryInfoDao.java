package com.wey.ten.era.rbac.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wey.ten.era.rbac.system.entity.vo.IndustryInfoVo;

@Mapper
public interface IndustryInfoDao {
    List<IndustryInfoVo> getAll();
    IndustryInfoVo getDetail(Integer id);
}
