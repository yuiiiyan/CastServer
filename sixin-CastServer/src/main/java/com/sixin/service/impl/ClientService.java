/**  
 * @Title:  FingerPrintService.java   
 * @Package com.shuofang.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月5日 下午10:41:31   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.service.impl;

import com.sixin.dao.DaoSupport;
import com.sixin.entity.Client;
import com.sixin.entity.Page;
import com.sixin.service.ClientManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Shuofang
 *	TODO
 */

/** 用户管理
 */
@Service("clientService")
public class ClientService implements ClientManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/* (non-Javadoc)
	 * @see com.shuofang.service.FingerPrintManager#getAllUser(com.shuofang.entity.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllUser(Page page) throws Exception {
		// TODO 分页查询所有用户
		return (List<Client>) dao.findForList("ClientMapper.getAlllistPage", page);
	}

	/* (non-Javadoc)
	 * @see com.shuofang.service.FingerPrintManager#saveUser(com.shuofang.entity.FingerPrint)
	 */
	@Override
	public void saveUser(Client client) throws Exception {
		// TODO 保存新用户
		dao.save("ClientMapper.saveClient", client);
	}

	/* (non-Javadoc)
	 * @see com.shuofang.service.FingerPrintManager#editUser(com.shuofang.entity.FingerPrint)
	 */
	@Override
	public void editUser(Client client) throws Exception {
		// TODO 修改用户
		dao.update("ClientMapper.editClient", client);
	}

	/* (non-Javadoc)
	 * @see com.shuofang.service.FingerPrintManager#DeleteUser(java.lang.String)
	 */
	@Override
	public void DeleteUser(String clientid) throws Exception {
		// TODO 删除用户
		dao.delete("ClientMapper.deleteClient", clientid);
	}

}
