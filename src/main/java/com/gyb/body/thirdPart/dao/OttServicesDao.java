package com.gyb.body.thirdPart.dao;

import com.gyb.body.thirdPart.model.OttServicesModel;
import com.gyb.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OttServicesDao extends BaseDao<OttServicesModel>{
    List<OttServicesModel> selectListByType(@Param("ser_type") String ser_type);
}