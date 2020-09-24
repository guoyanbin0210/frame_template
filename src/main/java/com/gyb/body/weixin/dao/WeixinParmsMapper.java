
package com.gyb.body.weixin.dao;

import com.gyb.body.weixin.model.WeiXinEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeixinParmsMapper {

	
	@Select("select * from TWeixinParms")
	List<WeiXinEntity> selectByExample(@Param("id") String id);
	
	@Insert("update TWeixinParms a  set  a.accessToken = #{weixinParam.accessToken} where id  #{id}")
	public void updateByPrimaryKeySelective(@Param("weixinParam") WeiXinEntity weixinParam);
	
}
