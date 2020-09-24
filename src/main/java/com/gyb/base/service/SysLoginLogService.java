package com.gyb.base.service;

import com.gyb.base.model.SysLoginLogModel;

/**
 * Description:
 * Date: 2018-12-21
 * Time: 02:01
 */
public interface SysLoginLogService extends BaseService<SysLoginLogModel> {

    Integer selectCountByCreateBy(String create_by);

    SysLoginLogModel selectLastByCreateBy(String create_by);

}
