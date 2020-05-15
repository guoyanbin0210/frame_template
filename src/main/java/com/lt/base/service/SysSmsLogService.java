package com.lt.base.service;

import com.lt.base.model.SysSmsLogModel;

import java.util.HashMap;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2019-01-02
 * Time: 01:37
 */
public interface SysSmsLogService extends BaseService<SysSmsLogModel> {
    HashMap selectSendCount(String ssl_model_code);

}
