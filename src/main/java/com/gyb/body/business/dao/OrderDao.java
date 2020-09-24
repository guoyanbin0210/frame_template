package com.gyb.body.business.dao;

import com.gyb.body.business.model.OrderModel;
import com.gyb.base.dao.BaseDao;
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