/**  
 * @Title:  minaCastmain.java   
 * @Package com.nettycast.minathread   
 * @Description:    TODO 测试类
 * @author: Shuofang     
 * @date:   2019年1月10日 上午11:39:51   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Shuofang
 *	TODO
 */
public class minaCastmain
{
        private static Logger logger = LoggerFactory.getLogger(minaCastmain.class);
         
        public static void main(String[] args) throws Exception
        {
        	/*MinaCastThread mThread = new MinaCastThread(8900);
        	mThread.run();*/
        	/*byte c = 11;
        	String string = "0A0d";
        	System.out.println(Convert.byteToHexString(c));
        	byte[] bs = Convert.hexStringToBytes(string);
        	for(byte b:bs) {
        		System.out.print(b);
        	}*/
        	UnicastThread thread = new UnicastThread(8900);
        	thread.run();
        	/*InetAddress  address = null;
        	address = InetAddress.getLocalHost();
	    	 String localname=address.getHostName();
	         String localip=address.getHostAddress();
	         System.out.println("本机名称是："+ localname);
	         System.out.println("本机的ip是 ："+localip);*/
        	//CastListerning(1000);
        	//CastListerning(6970);
        }
}