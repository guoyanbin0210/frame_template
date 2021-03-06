package com.gyb.base.service;

import com.gyb.base.model.SysLoginModel;

import java.util.List;

/**
 * Description:
 * Date: 2018-12-17
 * Time: 11:36
 */
public interface SysLoginService extends BaseService<SysLoginModel>{
    SysLoginModel selectModelByUserName(String user_name);
    SysLoginModel selectDetailsById(String id);
    List<SysLoginModel> selectListDetails(SysLoginModel model);

}

