package com.gyb.body.weixin.service.impl;

import com.gyb.body.weixin.dao.Access_tokenDao;
import com.gyb.body.weixin.model.Access_tokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Access_tokenServiceImp {
	@Autowired
	private Access_tokenDao tokenDao;
	
	public Access_tokenEntity selectOne() {
		Access_tokenEntity token = new Access_tokenEntity();
		token = tokenDao.selectOne();
		return token;
	}

	public Integer update(Access_tokenEntity access_tokenPOJO) {
		tokenDao.update(access_tokenPOJO);
		return null;
	}
	
	public Integer updateTicket(Access_tokenEntity access_tokenPOJO) {
		tokenDao.updateTicket(access_tokenPOJO);
		return null;
	}
	

	public Integer insert(Access_tokenEntity access_tokenPOJO) {
		 tokenDao.insert(access_tokenPOJO);
		return null;
	}
}
