package com.lt.body.weixin.service;


import com.lt.base.service.BaseService;
import com.lt.body.weixin.model.OrderModel;

import java.math.BigDecimal;
import java.util.Map;

/**
 * * Created with GuoYanBin.
 * Description:停车场订单 service
 * Date: 2020-06-22
 * Time: 11:03
 */
public interface OrderService extends BaseService<OrderModel> {


    void updatePayment(String userId);

    Map wxPay(String spbill_create_ip, String openId, String orderNumber, int price);


    Integer updatePaySuccess(String orderNo, BigDecimal amount, String payType);

    Integer  updateAfterNow(String parkId);

    Integer  updateAppraise(String orderNo);

    OrderModel  selectByOrderNo(String orderNo);

    //根据用户id  停车场Id 查询当前所属停车场是否为续租
    OrderModel findDataByUserIdAndParkId(String userId, String parkId);


}
