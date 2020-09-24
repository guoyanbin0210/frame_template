package com.gyb.body.user.dao;

import com.gyb.body.user.model.UserIntegralModel;
import com.gyb.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:积分管理 mapper
 */
@Mapper
public interface UserIntegralDao extends BaseDao<UserIntegralModel>{
    UserIntegralModel selectOneByUserIdAndUiType(@Param("user_id") String user_id, @Param("ui_type") String ui_type);

    List<UserIntegralModel> selectCurrDayByUserIdAndUiType(@Param("user_id") String user_id, @Param("ui_type") String ui_type);

    List<UserIntegralModel> selectCurrMonthByUserIdAndUiType(@Param("user_id") String user_id, @Param("ui_type") String ui_type);

    Integer selectSumByUserId(@Param("user_id")String user_id);
}