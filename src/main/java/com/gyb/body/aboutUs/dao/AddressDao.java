package com.gyb.body.aboutUs.dao;

import com.gyb.base.dao.BaseDao;
import com.gyb.body.aboutUs.model.AddressModel;
import com.gyb.body.apiMoudel.ApiAddressMoudel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDao extends BaseDao<AddressModel>{

    //返回前端接口
    ApiAddressMoudel selectData();
}