package com.gyb.body.business.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gyb.base.model.BaseModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
/**
 * * Created with GuoYanBin.
 * Description:信息
 * Create Time: 2020-08-04 11:18
 */
@Component
@Data
public class OrderModel extends BaseModel{

    /**
    * 关联用户ID
    */
    private String userId;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 商品名称
    */
    private String goodsName;

    /**
    * 商品ID
    */
    private String goodsId;

    /**
    * 单价
    */
    private BigDecimal unitPrice;

    /**
    * 购买数量
    */
    private String buyCount;

    /**
    * 重量
    */
    private String weight;

    /**
    * 总价 
    */
    private BigDecimal amount;

    /**
    * 支付时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
    * 过期时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    /**
    * 是否支付
    */
    private String isPay;

    /**
    * 订单状态0 待付款，1已付款，2 未付款失效，3已完成
    */
    private String orderStatus;

    /**
    * 有效状态0默认 1有效 2无效
    */
    private String status;

    /**
    * 订单类型 1支付，2充值 ，3余额支付
    */
    private String orderType;

    /**
    * 备用1
    */
    private String Spare1;

    /**
    * 备用2
    */
    private String Spare2;

    /**
    * 备用3
    */
    private String Spare3;


}
