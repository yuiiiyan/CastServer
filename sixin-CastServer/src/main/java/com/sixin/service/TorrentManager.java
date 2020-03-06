/**  
 * @Title:  TorrentManager.java   
 * @Package com.shuofang.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月21日 下午6:13:34   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.service;

import com.sixin.entity.Page;
import com.sixin.entity.Torrent;

import java.util.List;

/**
 * @author Shuofang
 *	TODO
 */
public interface TorrentManager {
	/**获取会话信息(分页）
	 * @return
	 * @throws Exception
	 */
	public List<Torrent> getAllTorrents(Page page) throws Exception ;
	/**
	 * 保存山洪灾害数据信息
	 * @param torrent 采集信息
	 * @throws Exception
	 */
	public void saveTorrent(Torrent torrent) throws Exception ;
}
