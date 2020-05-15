package com.lt.base.service.impl;
import com.lt.base.dao.BaseDao;
import com.lt.base.dao.SysSmsCodeDao;
import com.lt.base.model.SysSmsCodeModel;
import com.lt.base.service.SysSmsCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * Created with GaoShan.
 * Description:
 * Date: 2019-01-04
 * Time: 11:22
 */
@Service
public class SysSmsCodeServiceImpl extends BaseServiceImpl<SysSmsCodeModel> implements SysSmsCodeService{
    @Resource 
    private SysSmsCodeDao dao;
    @Override
    public BaseDao<SysSmsCodeModel> getBaseDao() {
        return dao;
    }

    @Override
    public SysSmsCodeModel selectOneByPhoneAndCode(String phone, String code) {
        return dao.selectOneByPhoneAndCode(phone,code);
    }
}
