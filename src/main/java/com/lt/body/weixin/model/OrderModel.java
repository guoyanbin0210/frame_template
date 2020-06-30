package com.lt.body.weixin.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lt.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * * Created with GuoYanBin.
 * Description:停车场订单
 * Create Time: 2020-06-22 11:03
 */
@Component
@ApiModel
public class OrderModel extends BaseModel {
    @ApiParam(value ="订单编号")
    private String orderNo;

    @ApiParam(value ="用户ID")
    private String userId;

    @ApiParam(value ="停车场ID")
    private String parkId;

    @ApiParam(value ="停车场名称")
    private String parkName;

    @ApiParam(value ="租赁方式 1 日租，2 月租")
    private String leaseType;

    @ApiParam(value ="是否付款，0未付款 ，1 已付款")
    private String isPay;

    @ApiParam(value ="停车场地址")
    private String parkAddress;

    @ApiModelProperty(value ="支付时间")
    private Date payTime;

    @ApiParam(value ="支付类型 ， 微信 银行")
    private String payType;

    @ApiModelProperty(value ="支付金额",position = 0)
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiParam(value =" 订单时间开始")
    private Date orderTimeA;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiParam(value ="订单时间结束")
    private Date orderTimeB;

    @ApiParam(value ="状态")
    private String status;

    @ApiParam(value ="是否评价，0未评价 ，1 已评价")
    private String isAppraise;

    @ApiModelProperty(value ="单价",position = 0)
    private BigDecimal unitPrice;

    @ApiParam(value ="购买数量")
    private int buyCount;

    @ApiParam(value ="停车场照片")
    private String tb_park_pic;

    @ApiParam(value ="订单天数")
    private String btweenDays;


    public String getBtweenDays() {
        return btweenDays;
    }

    public void setBtweenDays(String btweenDays) {
        this.btweenDays = btweenDays;
    }

    public String getTb_park_pic() {
        return tb_park_pic;
    }

    public void setTb_park_pic(String tb_park_pic) {
        this.tb_park_pic = tb_park_pic;
    }


    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIsAppraise() {
        return isAppraise;
    }

    public void setIsAppraise(String isAppraise) {
        this.isAppraise = isAppraise;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setOrderNo(String orderNo){        this.orderNo=orderNo;    }
 
    public String getOrderNo(){        return orderNo;    }
 
    public void setUserId(String userId){        this.userId=userId;    }
 
    public String getUserId(){        return userId;    }
 
    public void setParkId(String parkId){        this.parkId=parkId;    }
 
    public String getParkId(){        return parkId;    }
 
    public void setParkName(String parkName){        this.parkName=parkName;    }
 
    public String getParkName(){        return parkName;    }
 
    public void setLeaseType(String leaseType){        this.leaseType=leaseType;    }
 
    public String getLeaseType(){        return leaseType;    }
 
    public void setIsPay(String isPay){        this.isPay=isPay;    }
 
    public String getIsPay(){        return isPay;    }
 
    public void setPayTime(Date payTime){        this.payTime=payTime;    }
 
    public Date getPayTime(){        return payTime;    }
 
    public void setPayType(String payType){        this.payType=payType;    }
 
    public String getPayType(){        return payType;    }
 
    public void setOrderTimeA(Date orderTimeA){        this.orderTimeA=orderTimeA;    }
 
    public Date getOrderTimeA(){        return orderTimeA;    }
 
    public void setOrderTimeB(Date orderTimeB){        this.orderTimeB=orderTimeB;    }
 
    public Date getOrderTimeB(){        return orderTimeB;    }

    public void setStatus(String status){        this.status=status;    }
 
    public String getStatus(){        return status;    }

    public String getParkAddress() {
        return parkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
    }
 
}
