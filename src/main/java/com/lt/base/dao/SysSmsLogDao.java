package com.lt.base.dao;

import com.lt.base.model.SysSmsLogModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2019-01-02
 * Time: 01:37
 */
@Mapper
public interface SysSmsLogDao extends BaseDao<SysSmsLogModel>{
    HashMap selectSendCount(@Param("ssl_model_code")String ssl_model_code);
}