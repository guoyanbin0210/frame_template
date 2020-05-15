package com.lt.body.user.dao;

import com.lt.body.user.model.UserIntegralModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:积分管理 mapper
 * Date: 2019-05-20
 * Time: 04:58
 */
@Mapper
public interface UserIntegralDao extends BaseDao<UserIntegralModel>{
    UserIntegralModel selectOneByUserIdAndUiType(@Param("user_id") String user_id, @Param("ui_type") String ui_type);

    List<UserIntegralModel> selectCurrDayByUserIdAndUiType(@Param("user_id") String user_id, @Param("ui_type") String ui_type);

    List<UserIntegralModel> selectCurrMonthByUserIdAndUiType(@Param("user_id") String user_id, @Param("ui_type") String ui_type);

    Integer selectSumByUserId(@Param("user_id")String user_id);
}