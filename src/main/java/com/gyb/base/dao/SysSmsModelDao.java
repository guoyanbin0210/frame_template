package com.gyb.base.dao;

import com.gyb.base.model.SysSmsModelModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysSmsModelDao extends BaseDao<SysSmsModelModel>{
    SysSmsModelModel selectModelByCode(@Param("ssm_code")String ssm_code);
}