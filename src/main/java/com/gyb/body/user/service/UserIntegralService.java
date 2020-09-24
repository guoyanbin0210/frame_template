package com.gyb.body.user.service;

import com.gyb.base.service.BaseService;
import com.gyb.body.user.model.UserIntegralModel;

import java.util.List;


public interface UserIntegralService extends BaseService<UserIntegralModel> {
    /**
     * 主要针对 注册
     *
     * @param user_id
     * @param ui_type
     * @return
     */
    UserIntegralModel selectOneByUserIdAndUiType(String user_id, String ui_type);

    /**
     * 当天
     *
     * @param user_id
     * @param ui_type
     * @return
     */
    List<UserIntegralModel> selectCurrDayByUserIdAndUiType(String user_id, String ui_type);


    /**
     * 针对 月签到
     * @param user_id
     * @param ui_type
     * @return
     */
    List<UserIntegralModel> selectCurrMonthByUserIdAndUiType(String user_id, String ui_type);


    /**
     * 查询用户当前总积分
     * @param user_id
     * @return
     */
    Integer selectSumByUserId(String user_id);
}
