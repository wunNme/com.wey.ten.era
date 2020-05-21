package com.wey.ten.era.rbac.system.service.impl;

import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.dao.IndustryInfoDao;
import com.wey.ten.era.rbac.system.service.IndustryInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndustryInfoServiceImpl  implements IndustryInfoService {

    @Autowired
    private IndustryInfoDao industryInfoDao;

    @Override
    public ResultObject getAll() {
        ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
        resultObject.setData(industryInfoDao.getAll());
        return resultObject;
    }
}
