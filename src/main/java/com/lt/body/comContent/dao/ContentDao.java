package com.lt.body.comContent.dao;

import com.lt.body.apiMoudel.ApiContentCopyModel;
import com.lt.body.apiMoudel.ApiContentModel;
import com.lt.body.comContent.model.ContentModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:文章管理 mapper
 */
@Mapper
public interface ContentDao extends BaseDao<ContentModel>{

    //返回后台数据
    List<ContentModel> selectDataByHtml(@Param("tb_menu_id") String tb_menu_id,@Param("id")String id,@Param("keyWord")String keyWord);

    //根据二级菜单查询列表
    List<ApiContentModel> selectDataByMenuId(@Param("menu_id") String menu_id);

    //检查菜单id是否存在
    Integer selectMenuId(@Param("tb_parent_id") String tb_parent_id);

    ApiContentCopyModel selectDataById(@Param("menu_id") String menu_id);



}