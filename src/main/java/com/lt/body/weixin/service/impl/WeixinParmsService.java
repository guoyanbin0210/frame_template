
package com.lt.body.weixin.service.impl;

import com.lt.body.weixin.dao.WeixinParmsMapper;
import com.lt.body.weixin.model.WeiXinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeixinParmsService {
	@Autowired
	private WeixinParmsMapper weixinParmsMapper;

	public List<WeiXinEntity> selectByExample(String id){
		List<WeiXinEntity> list = new ArrayList<WeiXinEntity>();
		list = weixinParmsMapper.selectByExample(id);
		return list;
	}
	
	
	public void updateByPrimaryKeySelective(WeiXinEntity tWeixinParms){
		
		weixinParmsMapper.updateByPrimaryKeySelective(tWeixinParms);
		
		
	}  
	
}
