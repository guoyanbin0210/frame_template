package com.gyb.body.business.dao;

import com.gyb.body.business.model.UserModel;
import com.gyb.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
/**
 * * Created with GuoYanBin.
 * Description:信息 mapper
 * Date: 2020-08-07
 * Time: 10:55
 */
@Mapper
public interface UserDao extends BaseDao<UserModel>{
}