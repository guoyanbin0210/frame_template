package com.gyb.body.user.utils;

import com.gyb.body.user.model.IntegralRuleModel;
import com.gyb.body.user.model.UserIntegralModel;
import com.gyb.body.user.service.IntegralRuleService;
import com.gyb.body.user.service.UserIntegralService;

import java.util.List;


public class IntegralHelper {
    private static final int code_0 = 0;//默认
    private static final String STATUS_FAILED = "FAILED";

    private static final int code_1 = 1;//成功
    private static final String STATUS_SUCCESS = "SUCCESS";

    private static final int code_101 = 101;//到达上限


    /**
     * 注册 仅仅注册 一次
     *
     * @param userIntegralService 积分记录
     * @param integralRuleService 积分规则
     * @param user_id             用户id
     * @return
     */
    public static int doChangeIntegralRegister(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id) {
        String ruleId = "A8A366FFC0884324ADF34EF3E143788E";
        IntegralRuleModel ruleModel = integralRuleService.selectById(ruleId);
        if (ruleModel != null) {//表示存在这一条规则
            UserIntegralModel userIntegralModel = userIntegralService.selectOneByUserIdAndUiType(user_id, ruleId);
            if (userIntegralModel == null) {//表示还没有记录
                doChangeIntegral(userIntegralService, user_id, ruleId, ruleModel.getIr_val());
                return ruleModel.getIr_val();
            }
        }
        return code_0;
    }

    /**
     * 签到 每天一次
     *
     * @param userIntegralService
     * @param integralRuleService
     * @param user_id
     * @return
     */
    public static int doChangeIntegralSign(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id) {
        String ruleId = "5966329F85874AC9BE6F0FD62215FDDA";
        IntegralRuleModel ruleModel = integralRuleService.selectById(ruleId);
        if (ruleModel != null) {//表示存在这一条规则
            List<UserIntegralModel> userIntegralModels = userIntegralService.selectCurrDayByUserIdAndUiType(user_id, ruleId);
            if (userIntegralModels != null) {
                if (userIntegralModels.size() == 0) {//表示还没有记录
                    doChangeIntegral(userIntegralService, user_id, ruleId, ruleModel.getIr_val());
                    return ruleModel.getIr_val();
                }
            }

        }
        return code_0;
    }


    /**
     * 查询签到 月历
     *
     * @param userIntegralService
     * @param integralRuleService
     * @param user_id
     * @return
     */
    public static List doSelectIntegralSign(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id) {

        String ruleId = "5966329F85874AC9BE6F0FD62215FDDA";
        IntegralRuleModel ruleModel = integralRuleService.selectById(ruleId);
        if (ruleModel != null) {//表示存在这一条规则
            return userIntegralService.selectCurrMonthByUserIdAndUiType(user_id, ruleId);
        }

        return null;
    }


    /**
     * 分享 每天三次
     *
     * @param userIntegralService
     * @param integralRuleService
     * @param user_id
     * @return
     */
    public static int doChangeIntegralShare(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id) {
        return doMore(userIntegralService, integralRuleService, user_id, "6E4C6DAC776A4A8BA83078330FDCA3A4");
    }


    /**
     * 评论 每天5次
     *
     * @param userIntegralService
     * @param integralRuleService
     * @param user_id
     * @return
     */
    public static int doChangeIntegralComment(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id) {
        return doMore(userIntegralService, integralRuleService, user_id, "DC6FA627EBEC4474BBE61D53E2FE09F9");
    }


    /**
     * 活动 每天1次
     *
     * @param userIntegralService
     * @param integralRuleService
     * @param user_id
     * @return
     */
    public static int doChangeIntegralActivity(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id) {
        return doMore(userIntegralService, integralRuleService, user_id, "C94232FDE20843BB879D198759691BB3");
    }


    /**
     * 阅读 每天1次
     *
     * @param userIntegralService
     * @param integralRuleService
     * @param user_id
     * @return
     */
    public static int doChangeIntegralRead(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id) {
        return doMore(userIntegralService, integralRuleService, user_id, "E4837EEFC9724EA9A01E63F112884AED");
    }


    private static int doMore(UserIntegralService userIntegralService, IntegralRuleService integralRuleService, String user_id, String ruleId) {
        IntegralRuleModel ruleModel = integralRuleService.selectById(ruleId);
        if (ruleModel != null) {//表示存在这一条规则
            List<UserIntegralModel> userIntegralModels = userIntegralService.selectCurrDayByUserIdAndUiType(user_id, ruleId);
            if (userIntegralModels != null) {//表示还没有记录
                if (userIntegralModels.size() < ruleModel.getIr_day_count()) {//如果还有次数继续
                    doChangeIntegral(userIntegralService, user_id, ruleId, ruleModel.getIr_val());
                    return ruleModel.getIr_val();
                }
            }
        }
        return code_0;
    }


    /**
     * @param userIntegralService 积分记录
     * @param user_id             用户id
     * @param ui_type             规则id
     * @param change              变化值
     * @return
     */
    public static int doChangeIntegral(UserIntegralService userIntegralService, String user_id, String ui_type, int change) {
        //根据 积分规则id查询 积分规则id
        UserIntegralModel userIntegralModel = new UserIntegralModel();
        userIntegralModel.init(userIntegralModel);
        userIntegralModel.setUser_id(user_id);
        userIntegralModel.setUi_type(ui_type);
        userIntegralModel.setUi_change(change);
        return userIntegralService.insert(userIntegralModel);
    }
}
