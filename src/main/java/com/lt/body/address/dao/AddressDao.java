package com.lt.body.address.dao;

import com.lt.body.address.model.AddressModel;
import com.lt.base.dao.BaseDao;
import com.lt.body.apiMoudel.ApiAddressMoudel;
import org.apache.ibatis.annotations.Mapper;
/**
 * Created with GaoShan.
 * Description:底部信息 mapper
 * Date: 2020-03-02
 * Time: 09:02
 */
@Mapper
public interface AddressDao extends BaseDao<AddressModel>{

    //返回前端接口
    ApiAddressMoudel selectData();
}