/**  
 * @Title:  Client.java   
 * @Package com.shuofang.entity   
 * @Description:    TODO(终端信息工厂)   
 * @author: Shuofang     
 * @date:   2019年1月3日 下午3:46:43   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.entity;

import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * @author Shuofang
 *	TODO 终端模型
 */
public class Client {
	private int clientid;//终端ID号
	private String clietname;//终端用户名
	private Date creatdate;//创建日期
	private Date finallogindate;//最后登录记录
	private String note;//备注
	private String clientinfo;//备注
	private String imei;//备注
	private String rdsdata;//rds信息，8+5共13位字节
	private String leddata;//LED显示字符信息
	private boolean isregister;//是否登录
	private IoSession ioSession;//连接信息
	/**
	 * @return the clientid
	 */
	public int getClientid() {
		return clientid;
	}
	/**
	 * @param clientid the clientid to set
	 */
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	/**
	 * @return the clietname
	 */
	public String getClietname() {
		return clietname;
	}
	/**
	 * @param clietname the clietname to set
	 */
	public void setClietname(String clietname) {
		this.clietname = clietname;
	}
	/**
	 * @return the creatdate
	 */
	public Date getCreatdate() {
		return creatdate;
	}
	/**
	 * @param creatdate the creatdate to set
	 */
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	/**
	 * @return the finallogindate
	 */
	public Date getFinallogindate() {
		return finallogindate;
	}
	/**
	 * @param finallogindate the finallogindate to set
	 */
	public void setFinallogindate(Date finallogindate) {
		this.finallogindate = finallogindate;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the clientinfo
	 */
	public String getClientinfo() {
		return clientinfo;
	}
	/**
	 * @param clientinfo the clientinfo to set
	 */
	public void setClientinfo(String clientinfo) {
		this.clientinfo = clientinfo;
	}
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the rdsdata
	 */
	public String getRdsdata() {
		return rdsdata;
	}
	/**
	 * @param rdsdata the rdsdata to set
	 */
	public void setRdsdata(String rdsdata) {
		this.rdsdata = rdsdata;
	}
	/**
	 * @return the leddata
	 */
	public String getLeddata() {
		return leddata;
	}
	/**
	 * @param leddata the leddata to set
	 */
	public void setLeddata(String leddata) {
		this.leddata = leddata;
	}
	/**
	 * @return the isregister
	 */
	public boolean isIsregister() {
		return isregister;
	}
	/**
	 * @param isregister the isregister to set
	 */
	public void setIsregister(boolean isregister) {
		this.isregister = isregister;
	}
	/**
	 * @return the ioSession
	 */
	public IoSession getIoSession() {
		return ioSession;
	}
	/**
	 * @param ioSession the ioSession to set
	 */
	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}
	
	
}
