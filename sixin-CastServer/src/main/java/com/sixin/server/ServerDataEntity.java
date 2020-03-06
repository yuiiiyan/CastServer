/**  
 * @Title:  ServerDataEntity.java   
 * @Package com.shuofang.server   
 * @Description:    TODO(终端信息存储与通信全局管理)   
 * @author: Shuofang     
 * @date:   2019年1月18日 下午7:54:42   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.server;

import java.net.InetSocketAddress;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author Shuofang
 *	TODO
 */
public class ServerDataEntity {
	private static ExecutorService executorService =  null;//终端交互处理线程池
	private static MinaCastThread minaCastThread;//服务器端口监听Mina处理Acceptor
	private static UnicastThread unicastThread = null;//服务器端口监听netty
    private static Map<InetSocketAddress, String> IMEI_IP = new Hashtable<InetSocketAddress, String>();//IMEI以及对应IP
	/**
	 * 将线程加入线程池运行
	 * @param thread
	 */
	public static void putThreadintoPool(Runnable thread){
		if(executorService!=null)
		executorService.execute(thread);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * @return the executorService
	 */
	public static ExecutorService getExecutorService() {
		return executorService;
	}
	/**
	 * @param executorService the executorService to set
	 */
	public static void setExecutorService(ExecutorService executorService) {
		ServerDataEntity.executorService = executorService;
	}
	/**
	 * @return the minaCastThread
	 */
	public static MinaCastThread getMinaCastThread() {
		return minaCastThread;
	}
	/**
	 * @param minaCastThread the minaCastThread to set
	 */
	public static void setMinaCastThread(MinaCastThread minaCastThread) {
		ServerDataEntity.minaCastThread = minaCastThread;
	}

	/**
	 * @return the unicastThread
	 */
	public static UnicastThread getUnicastThread() {
		return unicastThread;
	}
	/**
	 * @param unicastThread the unicastThread to set
	 */
	public static void setUnicastThread(UnicastThread unicastThread) {
		ServerDataEntity.unicastThread = unicastThread;
	}

	/**
	 * @return the iMEI_IP
	 */
	public static Map<InetSocketAddress, String> getIMEI_IP() {
		return IMEI_IP;
	}

	/**
	 * @param iMEI_IP the iMEI_IP to set
	 */
	public static void setIMEI_IP(Map<InetSocketAddress, String> iMEI_IP) {
		IMEI_IP = iMEI_IP;
	}

}
