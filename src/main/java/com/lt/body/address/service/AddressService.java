package com.lt.body.address.service;
import com.lt.body.address.model.AddressModel;
import com.lt.base.service.BaseService;
import com.lt.body.apiMoudel.ApiAddressMoudel;

/**
 * Created with GaoShan.
 * Description:底部信息 service
 * Date: 2020-03-02
 * Time: 09:02
 */
public interface AddressService extends BaseService<AddressModel>{

    ApiAddressMoudel findData();

}