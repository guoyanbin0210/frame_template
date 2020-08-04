package com.lt.body.business.dao;

import com.lt.body.business.model.OrderModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
/**
 * * Created with GuoYanBin.
 * Description:信息 mapper
 * Date: 2020-08-04
 * Time: 11:18
 */
@Mapper
public interface OrderDao extends BaseDao<OrderModel>{
}