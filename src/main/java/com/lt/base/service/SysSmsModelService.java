package com.lt.base.service;
import com.lt.base.model.SysSmsModelModel;

/**
 * Description:
 * Date: 2019-02-14
 * Time: 09:32
 */
public interface SysSmsModelService extends BaseService<SysSmsModelModel>{


    SysSmsModelModel selectModelByCode(String ssm_code);


}
