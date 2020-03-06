/**  
 * @Title:  FingerPrintManager.java   
 * @Package com.shuofang.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月5日 下午10:38:04   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.service;

import com.sixin.entity.Client;
import com.sixin.entity.Page;

import java.util.List;

/**
 * @author Shuofang
 *	TODO
 */

/** 用户接口类
 */
public interface ClientManager {
	/**获取终端用户信息
	 * @return
	 * @throws Exception
	 */
	public List<Client> getAllUser(Page page) throws Exception ;
	/**
	 * 新建保存用户信息
	 * @param fingerPrint 用户信息
	 * @throws Exception
	 */
	public void saveUser(Client client) throws Exception ;
	/**
	 * 修改用户信息
	 * @param fingerPrint 用户信息
	 * @throws Exception
	 */
	public void editUser(Client client) throws Exception ;
	/**
	 * 删除用户信息
	 * @param fingerID 用户ID
	 * @throws Exception
	 */
	public void DeleteUser(String clientid) throws Exception ;
}
