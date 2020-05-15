package com.lt.body.user.service;

import com.lt.base.service.BaseService;
import com.lt.body.user.model.UserIntegralModel;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:积分管理 service
 * Date: 2019-05-20
 * Time: 04:58
 */
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
