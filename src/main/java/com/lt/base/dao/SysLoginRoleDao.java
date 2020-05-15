package com.lt.base.dao;

import com.lt.base.model.SysLoginRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:用户角色关系
 * Date: 2018-12-20
 * Time: 10:16
 */
@Mapper
public interface SysLoginRoleDao extends BaseDao<SysLoginRoleModel>{

    List<SysLoginRoleModel> selectByLoginId(@Param("login_id")String login_id);

    Integer deleteRoleId(@Param("role_id")String role_id);

    Integer deleteByLoginIdAndRoleId(@Param("login_id")String login_id,@Param("role_id")String role_id);

}