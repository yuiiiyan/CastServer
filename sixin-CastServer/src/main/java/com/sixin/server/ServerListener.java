/**  
 * @Title:  ServerListener.java   
 * @Package com.shuofang.server   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月18日 下午7:51:45   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.server;

import com.sixin.util.Const;
import com.sixin.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;

/**
 * @author Shuofang
 *	TODO
 */
public class ServerListener implements ServletContextListener{
    private static Logger logger = LoggerFactory.getLogger(ServerListener.class);

	/**
	 * 关闭监听调用
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//关闭线程池
		ServerDataEntity.getExecutorService().shutdown();
		//关闭端口监听
		if (ServerDataEntity.getMinaCastThread().getAcceptor() != null){
			ServerDataEntity.getMinaCastThread().getAcceptor().unbind();
			ServerDataEntity.getMinaCastThread().getAcceptor().setCloseOnDeactivation(true);
			ServerDataEntity.getMinaCastThread().getAcceptor().dispose(true);
		}
	}

	/**
	 * 初始化监听调用
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("初始化监听");
		int port = Integer.parseInt(Tools.GetValueByKey(Const.CONFIG, "Port"));
		logger.info("获取端口"+port);
		MinaCastThread castThread = new MinaCastThread(port);
		logger.info("创建minaThread");
		//UnicastThread unicastThread= new UnicastThread(port);
		ServerDataEntity.setExecutorService(Executors.newCachedThreadPool());//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
		logger.info("创建线程池");
		ServerDataEntity.setMinaCastThread(castThread);
		castThread.run();
		logger.info("minaThread运行");
		/*ServerDataEntity.setUnicastThread(unicastThread);
		unicastThread.start();
		logger.info("UnicastThread启动监听");*/
	}

}
