package com.wey.ten.era.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.home.dao.AdRotationDao;
import com.wey.ten.era.home.entity.AdRotation;
import com.wey.ten.era.home.service.IAdRotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
public class AdRotationServiceImpl extends ServiceImpl<AdRotationDao, AdRotation> implements IAdRotationService
{
    @Autowired
    private AdRotationDao adRotationDao;

    public ResultObject select(){
        ResultObject object = new ResultObject(ErrorCode.SUCCESS);
        try{
            object.setData(adRotationDao.selectByExample());
            object.setErrorCode("查询成功");
        }catch (Exception e){
            object.setErrorCode("查询失败");
        }
        return object;
    }
}
