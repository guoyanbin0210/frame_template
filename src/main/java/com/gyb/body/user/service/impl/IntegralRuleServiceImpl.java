package com.gyb.body.user.service.impl;
import com.gyb.body.user.dao.IntegralRuleDao;
import com.gyb.body.user.model.IntegralRuleModel;
import com.gyb.body.user.service.IntegralRuleService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;



@Service
public class IntegralRuleServiceImpl extends BaseServiceImpl<IntegralRuleModel> implements IntegralRuleService{
    @Resource 
    private IntegralRuleDao dao;
    @Override
    public BaseDao<IntegralRuleModel> getBaseDao() {
        return dao;
    }
}
