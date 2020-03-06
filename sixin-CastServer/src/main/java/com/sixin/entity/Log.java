package com.sixin.entity;

import java.util.Date;

/**
 * 日志类
 * @ClassName Log
 * @Description TODO
 * @date 2016年11月15日 下午1:42:52
 */
public class Log {
	private String logid;//日志编号
	private String clientid;//用户编号
	private String logtype;//日志类别：登陆日志，操作日志
	private String functions;//功能模块
	private Date logtime;//生成时间
	private String remark;//备注
	
	/**
	 * @return the logtime
	 */
	public Date getLogtime() {
		return logtime;
	}
	/**
	 * @param logtime the logtime to set
	 */
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	public String getLogtype() {
		return logtype;
	}
	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}
	public String getFunction() {
		return functions;
	}
	public void setFunction(String functions) {
		this.functions = functions;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the logid
	 */
	public String getLogid() {
		return logid;
	}
	/**
	 * @param logid the logid to set
	 */
	public void setLogid(String logid) {
		this.logid = logid;
	}
	/**
	 * @return the clientid
	 */
	public String getClientid() {
		return clientid;
	}
	/**
	 * @param clientid the clientid to set
	 */
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	
	
	
}
