package com.lt.base.service;

import com.lt.base.model.BaseDirModel;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:字典 service
 * Date: 2019-05-07
 * Time: 02:40
 */
public interface BaseDirService extends BaseService<BaseDirModel> {
    BaseDirModel selectOneByName(String bd_name);

    List<BaseDirModel> selectListByType(String bd_type);

    List<BaseDirModel> selectListByTypeAndAvailable(String bd_type, Integer base_available);

    List<BaseDirModel> selectListLikeName(String bd_name);


    List<BaseDirModel> selectListByTypeAndAvailableAndBdData(String bd_type, Integer base_available, String bd_data);


    /**
     * NEWS_TYPE_SECOND
     * 查询资讯 二级类型
     *
     * @param bd_type
     * @param p_id
     * @param base_available
     * @return
     */
    List<BaseDirModel> selectListByPTypeAndPId(String bd_type,String p_id, Integer base_available);


}
