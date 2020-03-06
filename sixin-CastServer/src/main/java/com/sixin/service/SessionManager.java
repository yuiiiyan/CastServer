/**  
 * @Title:  SessionManager.java   
 * @Package com.shuofang.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月3日 下午4:47:26   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.service;

import com.sixin.entity.Page;
import com.sixin.entity.Session;

import java.util.List;

/**
 * @author Shuofang
 *	TODO
 */

/** 会话接口类
 */
public interface SessionManager {
	/**获取会话信息(分页）
	 * @return
	 * @throws Exception
	 */
	public List<Session> getAllSessions(Page page) throws Exception ;
	/**
	 * 保存会话
	 * @param session 会话信息
	 * @throws Exception
	 */
	public void saveSession(Session session) throws Exception ;
}
