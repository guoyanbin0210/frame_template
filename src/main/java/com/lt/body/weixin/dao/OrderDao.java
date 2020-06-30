package com.lt.body.weixin.dao;

import com.lt.base.dao.BaseDao;
import com.lt.body.weixin.model.OrderModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * * Created with GuoYanBin.
 * Description:停车场订单 mapper
 * Date: 2020-06-22
 * Time: 11:03
 */
@Mapper
public interface OrderDao extends BaseDao<OrderModel> {

    @Update({"update sk_order set isPay = 1 , payTime = now() where id = #{id} "})
    void updatePayment(String id);

    @Update({"update sk_order set isPay = 1 ,payType =#{payType} , payTime = now() ,amount =#{amount} where orderNo = #{orderNo} "})
    Integer updatePaySuccess(@Param("orderNo") String orderNo, @Param("amount") BigDecimal amount, @Param("payType") String payType);

    @Update({"update sk_order set status = 0 where orderTimeB <= now() and status = 1 "})
    Integer updateAfterNow(@Param("parkId") String parkId);

    @Update({"update sk_order set isAppraise = 1 where orderNo  = #{orderNo} "})
    Integer updateAppraise(String orderNo);

    OrderModel selectCountByUserIdAndParkId(@Param("userId") String userId, @Param("parkId") String parkId);

    @Select({"select * from sk_order where orderNo  = #{orderNo} "})
    OrderModel  selectByOrderNo(String orderNo);

}