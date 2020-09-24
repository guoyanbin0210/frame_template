package com.gyb.base.service.impl;

import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.BaseService;

import java.util.ArrayList;

/**
 * Description:
 * Date: 2018-11-28
 * Time: 9:47
 */
public abstract class BaseServiceImpl<Model> implements BaseService<Model> {
    public abstract BaseDao<Model> getBaseDao();

    @Override
    public Integer insert(Model baseModel) {
        return getBaseDao().insert(baseModel);
    }

    @Override
    public Integer delete(String id) {
        return getBaseDao().delete(id);
    }

    @Override
    public Integer update(Model baseModel) {
        return getBaseDao().update(baseModel);
    }

    @Override
    public ArrayList<Model> selectAll() {
        return getBaseDao().selectAll();
    }

    @Override
    public Model selectById(String id) {
        return getBaseDao().selectById(id);
    }

    @Override
    public ArrayList<Model> selectListByModel(Model model) {
        return getBaseDao().selectListByModel(model);
    }

    @Override
    public ArrayList<Model> selectListByKeyWord(String keyWord) {
        return getBaseDao().selectListByKeyWord(keyWord);
    }

    @Override
    public ArrayList<Model> selectListByModelAndKeyWord(Model model, String keyWord) {
        return getBaseDao().selectListByModelAndKeyWord(model, keyWord);
    }

    @Override
    public ArrayList<Model> selectListByUserNameAndKeyWord(String userName, String keyWord) {
        return getBaseDao().selectListByUserNameAndKeyWord(userName, keyWord);
    }
}
