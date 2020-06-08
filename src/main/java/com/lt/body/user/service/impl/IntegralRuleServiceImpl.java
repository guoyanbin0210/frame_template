package com.lt.body.user.service.impl;
import com.lt.body.user.dao.IntegralRuleDao;
import com.lt.body.user.model.IntegralRuleModel;
import com.lt.body.user.service.IntegralRuleService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
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
