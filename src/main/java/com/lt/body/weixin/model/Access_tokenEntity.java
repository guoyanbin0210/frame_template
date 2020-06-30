package com.lt.body.weixin.model;

import java.io.Serializable;
import java.util.Date;

public class Access_tokenEntity implements Serializable {
	
    private String appId;
    private String accessToken;
    private Date createTime;
    private String ticket;
    private Date ticketCreateTime;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getTicketCreateTime() {
		return ticketCreateTime;
	}

	public void setTicketCreateTime(Date ticketCreateTime) {
		this.ticketCreateTime = ticketCreateTime;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accessToken=").append(accessToken);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

}