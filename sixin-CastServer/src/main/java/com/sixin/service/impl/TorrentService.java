/**  
 * @Title:  TorrentService.java   
 * @Package com.shuofang.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月21日 下午6:17:55   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.service.impl;

import com.sixin.dao.DaoSupport;
import com.sixin.entity.Page;
import com.sixin.entity.Torrent;
import com.sixin.service.TorrentManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Shuofang
 *	TODO
 */
@Service("torrentService")
public class TorrentService implements TorrentManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/* (non-Javadoc)
	 * @see com.shuofang.service.TorrentManager#getAllTorrents(com.shuofang.entity.Page)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Torrent> getAllTorrents(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<Torrent>) dao.findForList("TorrentMapper.getAlllistPage", page);
	}

	/* (non-Javadoc)
	 * @see com.shuofang.service.TorrentManager#saveSession(com.shuofang.entity.Torrent)
	 */
	@Override
	public void saveTorrent(Torrent torrent) throws Exception {
		// TODO Auto-generated method stub
		dao.save("TorrentMapper.saveTorrent", torrent);
	}
	
}
