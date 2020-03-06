/**  
 * @Title:  LogManager.java   
 * @Package com.shuofang.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月3日 下午4:17:21   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.service;

import com.sixin.entity.Log;
import com.sixin.entity.Page;
import com.sixin.util.PageData;

import java.util.List;

/**
 * @author Shuofang
 *	TODO
 */

/** 日志接口类
 */
public interface LogManager {
	/**获取日志信息(分页）
	 * @return
	 * @throws Exception
	 */
	public List<Log> getAllLogs(Page page) throws Exception ;
	/**通过指纹用户获取日志信息(分页）
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getLogsbyFingerId(Page page) throws Exception;
	/**获取用户通过与未通过记录
	 * @return
	 * @throws Exception
	 */
	public PageData getUserPassLogsNum(Page page) throws Exception;
	/**获取用户日志信息(分页）
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getUserLogs(Page page) throws Exception;
	/**
	 * 保存日志
	 * @param fingerId 操作指纹ID
	 * @param logtype 操作类别
	 * @param function 功能模块
	 * @param remark 具体内容
	 * @throws Exception
	 */
	public void saveLog(String fingerId, String logtype, String function, String remark) throws Exception ;
	
}
