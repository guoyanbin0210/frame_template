package com.lt.body.business.model;
import com.lt.base.model.BaseModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
/**
 * * Created with GuoYanBin.
 * Description:信息
 * Create Time: 2020-08-07 10:55
 */
@Component
@Data
public class UserModel extends BaseModel{

    /**
    * userId--微信就是openId
    */
    private String userId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 用户名
    */
    private String wxUserName;

    /**
    * 身份证号
    */
    private String cardId;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 头像
    */
    private String headImg;

    /**
    * 身份证A
    */
    private String cardUrlA;

    /**
    * 身份证B
    */
    private String cardUrlB;

    /**
    * 用户类型
    */
    private String userType;

    /**
    * 审核状态 012
    */
    private String verifyStatus;

    /**
    * 审核原因
    */
    private String audit_cause;

    /**
    * 是否同意提示信息
    */
    private String isAgreeTip;

    /**
    * 账户余额
    */
    private BigDecimal surplusMoney;

    /**
    * 有效状态0默认 1有效 2无效
    */
    private String status;

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
