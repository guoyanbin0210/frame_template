package com.lt.base.dao;

import com.lt.base.model.SysSmsModelModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysSmsModelDao extends BaseDao<SysSmsModelModel>{
    SysSmsModelModel selectModelByCode(@Param("ssm_code")String ssm_code);
}