package com.lt.body.user.service.impl;
import com.lt.body.user.dao.UserIntegralDao;
import com.lt.body.user.model.UserIntegralModel;
import com.lt.body.user.service.UserIntegralService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created with GaoShan.
 * Description:积分管理 serviceImpl
 * Date: 2019-05-20
 * Time: 04:58
 */
@Service
public class UserIntegralServiceImpl extends BaseServiceImpl<UserIntegralModel> implements UserIntegralService{
    @Resource 
    private UserIntegralDao dao;
    @Override
    public BaseDao<UserIntegralModel> getBaseDao() {
        return dao;
    }

    @Override
    public UserIntegralModel selectOneByUserIdAndUiType(String user_id, String ui_type) {
        return dao.selectOneByUserIdAndUiType(user_id,ui_type);
    }

    @Override
    public List<UserIntegralModel> selectCurrDayByUserIdAndUiType(String user_id, String ui_type) {
        return dao.selectCurrDayByUserIdAndUiType(user_id,ui_type);
    }

    @Override
    public List<UserIntegralModel> selectCurrMonthByUserIdAndUiType(String user_id, String ui_type) {
        return dao.selectCurrMonthByUserIdAndUiType(user_id,ui_type);
    }

    @Override
    public Integer selectSumByUserId(String user_id) {
        return dao.selectSumByUserId(user_id);
    }
}
