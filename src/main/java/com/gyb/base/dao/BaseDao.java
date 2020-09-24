package com.gyb.base.dao;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;


public interface BaseDao<Model> {
    /**
     * 增
     *
     * @param model 实例对象
     * @return 插入成功数据
     */
    Integer insert(Model model);

    /**
     * 删
     *
     * @param id 数据主键
     */
    Integer delete(@Param("id") String id);

    Integer deleteByIds(List list);

    /**
     * 改
     *
     * @param model 实例对象
     */

    Integer update(Model model);


    /**
     * 查询全部数据 无条件
     *
     * @return 全部数据
     */
    ArrayList<Model> selectAll();

    /**
     * 查询一个根据id
     *
     * @param id 主键
     * @return 实例对象
     */
    Model selectById(@Param("id") String id);

    /**
     * 根据关键字查询数据 - 固定条件 模糊查询
     * 当固定条件包含关键字的时候，关键字失效，仅显示固定条件
     *
     * @param model 固定条件
     * @return 全部数据
     */
    ArrayList<Model> selectListByModel(Model model);

    /**
     * 根据关键字查询数据 -模糊查询
     *
     * @param keyWord 关键字
     * @return 全部数据
     */
    ArrayList<Model> selectListByKeyWord(@Param("keyWord") String keyWord);


    /**
     * 根据关键字查询数据 - 固定条件 模糊查询
     * 当固定条件包含关键字的时候，关键字失效，仅显示固定条件
     *
     * @param model   固定条件
     * @param keyWord 关键字
     * @return 全部数据
     */
    ArrayList<Model> selectListByModelAndKeyWord(Model model, @Param("keyWord") String keyWord);


    ArrayList<Model> selectListByUserNameAndKeyWord(@Param("userName")String userName, @Param("keyWord") String keyWord);



}
