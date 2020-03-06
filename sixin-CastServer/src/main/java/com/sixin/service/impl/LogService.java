package com.sixin.service.impl;

import com.sixin.dao.DaoSupport;
import com.sixin.entity.Log;
import com.sixin.entity.Page;
import com.sixin.service.LogManager;
import com.sixin.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/** 日志操作
 */
@Service("logService")
public class LogService implements LogManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/**
	 * 获取全部日志
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getAllLogs(Page page) throws Exception {
		return (List<Log>) dao.findForList("LogMapper.getAlllistPage", page);
	}
	/**
	 * 保存日志信息
	 */
	@Override
	public void saveLog(String fingerId,String logtype, String function,String remark) throws Exception {
		Log logs = new Log();
		if(fingerId != null)
			logs.setClientid(fingerId);
		logs.setFunction(function);
		logs.setLogtype(logtype);
		logs.setRemark(remark);
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		logs.setLogtime(new Date());
		dao.save("LogMapper.saveLog", logs);
	}
	/**
	 * 获取对应指纹全部日志
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getLogsbyFingerId(Page page) throws Exception{
		return (List<PageData>) dao.findForList("LogMapper.getLoglistPage", page);
	}
	/* (non-Javadoc)
	 * @see com.shuofang.service.LogManager#getUserLogs(com.shuofang.entity.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getUserLogs(Page page) throws Exception {
		// TODO 获取用户日志信息
		return (List<PageData>) dao.findForList("LogMapper.getUserlistPage", page);
	}
	/* (non-Javadoc)
	 * @see com.shuofang.service.LogManager#getUserPassLogsNum(com.shuofang.entity.Page)
	 */
	@Override
	public PageData getUserPassLogsNum(Page page) throws Exception {
		// TODO 获取用户通过与未通过记录
		return (PageData) dao.findForObject("LogMapper.getUserPassNum", page);
	}
	
}
