package com.lt.base.dao;

import com.lt.base.model.SysSmsCodeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysSmsCodeDao extends BaseDao<SysSmsCodeModel>{
    SysSmsCodeModel selectOneByPhoneAndCode(@Param("ssc_phone") String phone, @Param("ssl_code") String code);
}