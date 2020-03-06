/**  
 * @Title:  SessionService.java   
 * @Package com.shuofang.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月3日 下午4:50:28   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.service.impl;

import com.sixin.dao.DaoSupport;
import com.sixin.entity.Page;
import com.sixin.entity.Session;
import com.sixin.service.SessionManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Shuofang
 *	TODO
 */

/** 会话管理
 */
@Service("sessionService")
public class SessionService implements SessionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/* (non-Javadoc)
	 * @see com.shuofang.service.SessionManager#getAllSessions(com.shuofang.entity.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Session> getAllSessions(Page page) throws Exception {
		// TODO 查询全部会话
		return (List<Session>) dao.findForList("SessionMapper.getAlllistPage", page);
	}

	/* (non-Javadoc)
	 * @see com.shuofang.service.SessionManager#saveSession(com.shuofang.entity.Session)
	 */
	@Override
	public void saveSession(Session session) throws Exception {
		// TODO 保存会话
		dao.save("SessionMapper.saveSession", session);
	}
	
}
