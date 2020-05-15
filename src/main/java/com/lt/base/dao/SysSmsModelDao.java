package com.lt.base.dao;

import com.lt.base.model.SysSmsModelModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2019-02-14
 * Time: 09:32
 */
@Mapper
public interface SysSmsModelDao extends BaseDao<SysSmsModelModel>{
    SysSmsModelModel selectModelByCode(@Param("ssm_code")String ssm_code);
}