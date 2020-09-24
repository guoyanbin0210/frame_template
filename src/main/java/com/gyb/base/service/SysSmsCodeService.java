package com.gyb.base.service;

import com.gyb.base.model.SysSmsCodeModel;

/**
 * Description:
 * Date: 2019-01-04
 * Time: 11:22
 */
public interface SysSmsCodeService extends BaseService<SysSmsCodeModel> {


    SysSmsCodeModel selectOneByPhoneAndCode(String phone, String code);

}
