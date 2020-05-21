package com.wey.ten.era.home.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.home.entity.AdRotation;
import com.wey.ten.era.home.entity.vo.AdRotationVo;

/**
 * 订单Mapper接口
 *
 * @author ruoyi
 * @date 2020-05-18
 */
public interface IAdRotationService extends IService<AdRotation>
{
    public ResultObject select();
}
