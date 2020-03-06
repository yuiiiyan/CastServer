/**  
 * @Title:  Session.java   
 * @Package com.shuofang.entity   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月3日 下午4:01:11   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.entity;

import java.util.Date;

/**
 * @author Shuofang
 *	TODO
 */
public class Session {
	private int sessionid;//会话ID
	private int sessionuser;//会话用户
	private String sessiondata;//会话内容
	private Date sessiondate;//会话日期
	/**
	 * @return the sessionid
	 */
	public int getSessionid() {
		return sessionid;
	}
	/**
	 * @param sessionid the sessionid to set
	 */
	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}
	/**
	 * @return the sessionuser
	 */
	public int getSessionuser() {
		return sessionuser;
	}
	/**
	 * @param sessionuser the sessionuser to set
	 */
	public void setSessionuser(int sessionuser) {
		this.sessionuser = sessionuser;
	}
	/**
	 * @return the sessiondata
	 */
	public String getSessiondata() {
		return sessiondata;
	}
	/**
	 * @param sessiondata the sessiondata to set
	 */
	public void setSessiondata(String sessiondata) {
		this.sessiondata = sessiondata;
	}
	/**
	 * @return the sessiondate
	 */
	public Date getSessiondate() {
		return sessiondate;
	}
	/**
	 * @param sessiondate the sessiondate to set
	 */
	public void setSessiondate(Date sessiondate) {
		this.sessiondate = sessiondate;
	}
	
}
