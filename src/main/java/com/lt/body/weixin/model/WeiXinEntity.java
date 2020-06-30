package com.lt.body.weixin.model;

import java.io.Serializable;

public class WeiXinEntity implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	 	private String access_token;
	    private String ticket;
	    private String noncestr;// 必填，生成签名的随机串
	    private String timestamp;//必填，生成签名的时间戳
	    private String str;
	    private String signature;// 必填，签名
	    
		public String getAccess_token() {
			return access_token;
		}
		public String getTicket() {
			return ticket;
		}
		public String getNoncestr() {
			return noncestr;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public String getStr() {
			return str;
		}
		public String getSignature() {
			return signature;
		}
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		public void setTicket(String ticket) {
			this.ticket = ticket;
		}
		public void setNoncestr(String noncestr) {
			this.noncestr = noncestr;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public void setStr(String str) {
			this.str = str;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
	    
	    
}