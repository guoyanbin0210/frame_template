package com.lt.body.aboutUs.dao;

import com.lt.base.dao.BaseDao;
import com.lt.body.aboutUs.model.AddressModel;
import com.lt.body.apiMoudel.ApiAddressMoudel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDao extends BaseDao<AddressModel>{

    //返回前端接口
    ApiAddressMoudel selectData();
}