
package com.lt.body.weixin.dao;

import com.lt.body.weixin.model.Access_tokenEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Access_tokenDao {
	 
	
	@Select("select * from token ")
	public Access_tokenEntity selectOne();
	
	
	@Insert("insert into  token (appId,accessToken,createTime)  values (#{token.appId}, #{token.accessToken}, #{token.createTime})")
	public void insert(@Param("token") Access_tokenEntity token);
	
	@Insert("update  token set accessToken = #{token.accessToken},createTime = #{token.createTime} where appId =  #{token.appId}")
	public void update(@Param("token") Access_tokenEntity token);
	
	@Insert("update  token set ticket = #{token.ticket} ,ticketCreateTime = #{token.ticketCreateTime} where appId =  #{token.appId}")
	public void updateTicket(@Param("token") Access_tokenEntity token);
	
	

}
