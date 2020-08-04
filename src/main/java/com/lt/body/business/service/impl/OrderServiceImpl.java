package com.lt.body.business.service.impl;
import com.lt.body.business.dao.OrderDao;
import com.lt.body.business.model.OrderModel;
import com.lt.body.business.service.OrderService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
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
