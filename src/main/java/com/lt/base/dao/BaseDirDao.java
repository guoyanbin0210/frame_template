package com.lt.base.dao;

import com.lt.base.model.BaseDirModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:字典 mapper
 * Date: 2019-05-07
 * Time: 02:40
 */
@Mapper
public interface BaseDirDao extends BaseDao<BaseDirModel>{
    BaseDirModel selectOneByName(@Param("bd_name") String bd_name);

    List<BaseDirModel> selectListByType(@Param("bd_type")String bd_type);
    List<BaseDirModel> selectListLikeName(@Param("bd_name")String bd_name);

    List<BaseDirModel> selectListByTypeAndAvailable(@Param("bd_type")String bd_type, @Param("base_available")Integer base_available);

    List<BaseDirModel> selectListByTypeAndAvailableAndBdData(@Param("bd_type")String bd_type,@Param("base_available") Integer base_available, @Param("bd_data")String bd_data);

    List<BaseDirModel> selectListByPTypeAndPId(@Param("bd_type")String bd_type,@Param("p_id") String p_id,@Param("base_available") Integer base_available);
}
