package com.gyb.body.business.service.impl;
import com.gyb.body.business.dao.OrderDao;
import com.gyb.body.business.model.OrderModel;
import com.gyb.body.business.service.OrderService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * * Created with GuoYanBin.
 * Description:信息 serviceImpl
 * Date: 2020-08-04
 * Time: 11:18
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderModel> implements OrderService{
    @Resource 
    private OrderDao dao;
    @Override
    public BaseDao<OrderModel> getBaseDao() {
        return dao;
    }
}
