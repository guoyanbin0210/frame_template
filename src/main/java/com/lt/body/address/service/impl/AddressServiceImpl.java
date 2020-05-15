package com.lt.body.address.service.impl;
import com.lt.body.address.dao.AddressDao;
import com.lt.body.address.model.AddressModel;
import com.lt.body.address.service.AddressService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import com.lt.body.apiMoudel.ApiAddressMoudel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Created with GaoShan.
 * Description:底部信息 serviceImpl
 * Date: 2020-03-02
 * Time: 09:02
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressModel> implements AddressService{
    @Resource 
    private AddressDao dao;
    @Override
    public BaseDao<AddressModel> getBaseDao() {
        return dao;
    }

    @Override
    public ApiAddressMoudel findData() {
        return dao.selectData();
    }
}
