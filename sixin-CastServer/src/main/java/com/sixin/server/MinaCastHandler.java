/**  
 * @Title:  MinaCastTest.java   
 * @Package com.nettycast.cast   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月10日 上午10:15:20   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.server;

import com.sixin.entity.Session;
import com.sixin.entity.Torrent;
import com.sixin.service.SessionManager;
import com.sixin.service.TorrentManager;
import com.sixin.service.impl.SessionService;
import com.sixin.service.impl.TorrentService;
import com.sixin.util.Convert;
import com.sixin.util.SpringContextUtils;
import com.sixin.util.Tools;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Date;
import java.util.Map;

/**
 * @author Shuofang
 *	TODO
 */
public class MinaCastHandler extends IoHandlerAdapter
{
    private static Logger logger = LoggerFactory.getLogger(MinaCastHandler.class);
    public static final CharsetDecoder decoder = (Charset.forName("ISO-8859-1")).newDecoder();
    private SessionManager sessionservice = (SessionService) SpringContextUtils.getBeanByClass(SessionService.class);
    private TorrentManager torrentservice = (TorrentService) SpringContextUtils.getBeanByClass(TorrentService.class);
    private Map<InetSocketAddress, String> ImeiMap = ServerDataEntity.getIMEI_IP();

        //private int Number = 1;
    /**
     * MINA的异常回调方法。
     * <p>
     * 本类中将在异常发生时，立即close当前会话。
     * 
     * @param session 发生异常的会话
     * @param cause 异常内容
     * @see IoSession#close(boolean)
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception 
    {
        logger.error("[IMCORE]exceptionCaught捕获到错了，原因是："+cause.getMessage(), cause);
        session.closeNow();
    }
         
    /**
     * MINA框架中收到客户端消息的回调方法。
     * <p>
     * 本类将在此方法中实现完整的即时通讯数据交互和处理策略。
     * <p>
     * 为了提升并发性能，本方法将运行在独立于MINA的IoProcessor之外的线程池中，
     * 详见 {@link ServerLauncher#initAcceptor()}中的MINA设置代码 。
     * 
     * @param session 收到消息对应的会话引用
     * @param message 收到的MINA的原始消息封装对象，本类中是 {@link IoBuffer}对象
     * @throws Exception 当有错误发生时将抛出异常
     */
    @Override
    public void messageReceived(IoSession session, Object message)throws Exception 
    {

            /* GlobalInfoUtil.putThreadintoPool(new Runnable() {
				@Override
				public void run() {*/
					// TODO Auto-generated method stub
					//*********************************************** 接收数据
		            // 读取收到的数据
		            IoBuffer buffer = (IoBuffer) message;
		            byte[] content = new byte[buffer.limit()];
		            buffer.get(content);
		            byte[] returndata = null;
		            InetSocketAddress iPAddress = (InetSocketAddress)session.getRemoteAddress();
					String IP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
					String data = null;
					//session.setAttribute(IP, "IP");
					// TODO Auto-generated method stub
					String str = Convert.bytesToHexString(content);
					String src = new String(content);
					//session.setAttribute(IP, "IP");
					if(Convert.byteToHexString(content[0]).equals(ProtocolsToClient.PACKETHEAD))
					switch (Convert.byteToHexString(content[1])) {//类型判断
					case ProtocolsToClient.E_FM_RECIVE:
						logger.debug("应急FM调频接收RDS码/接收频率,源码："+str);
						if(content[4] == 1) {
							byte[] rds = Tools.subBytes(content, 5, 8);
							byte[] frequency = Tools.subBytes(content, 14, 5);
							data = "IP:"+IP+"，应急FM调频发送RDS码"+new String(rds)+",接收频率"+new String(frequency);
							returndata = content;
						}else {
							
						}
						break;
					case ProtocolsToClient.CLIENTREAD:
						logger.debug("获取终端RDS码/发射频率/发射功率/反射功率/工作电压（对系统只读）,源码："+str);
						if(content[4] == 1) {
							byte[] rds = Tools.subBytes(content, 5, 8);
							byte[] frequency = Tools.subBytes(content, 13, 5);
							byte[] power = Tools.subBytes(content, 18, 4);
							byte[] reflect = Tools.subBytes(content, 22, 3);
							byte[] voltage = Tools.subBytes(content, 25, 5);
							data = "IP:"+IP+"，RDS码"+new String(rds)+",接收频率"+new String(frequency)+",发射功率"+new String(power)+",反射功率"+new String(reflect)+",工作电压"+new String(voltage);
							returndata = Convert.hexStringToBytes(ProtocolsToClient.getTrue(ProtocolsToClient.CLIENTREAD,null));
						}else {
							
						}
						break;
					case ProtocolsToClient.IPCHANGE:
						logger.debug("获取终端IP（对系统可读可写）并为流媒体心跳包,源码："+str);
						if(content[4] == 1) {
							byte[] IMEI = Tools.subBytes(content, 5, 15);
							data = "IP:"+IP+"，IMEI码"+new String(IMEI);
							returndata = Convert.hexStringToBytes(ProtocolsToClient.getTrue(ProtocolsToClient.IPCHANGE,"55"));
						}else {
							
						}
						break;
					case ProtocolsToClient.CELLPOS:
						logger.debug("基站信息（LAC,CID）,源码："+str);
						if(content[4] == 1) {
							byte[] LAC = Tools.subBytes(content, 5, 5);
							byte[] CID = Tools.subBytes(content, 11, 5);
							data = "IP:"+IP+"，LAC"+new String(LAC)+"，CID"+new String(CID);
							returndata = Convert.hexStringToBytes(ProtocolsToClient.getTrue(ProtocolsToClient.CELLPOS,null));
						}else {
							
						}
						break;
					case ProtocolsToClient.OPENSTREAM:
						logger.debug("启动流媒体传输,源码："+str);
						break;
					case ProtocolsToClient.CLOSESTREAM:
						logger.debug("停止流媒体传输,源码："+str);
						break;
					case ProtocolsToClient.UPDATELED:
						logger.debug("LED字幕更新（终端主动请求获取）,源码："+str);
						break;
					case ProtocolsToClient.GETPARAMATER:
						logger.debug("物联网终端参数透传接口(终端主动请求获取),源码："+str);
						if(content[4] == 1) {
							byte[] IMEI = Tools.subBytes(content, 5, 15);
							data = "IP:"+IP+"，IMEI码"+new String(IMEI)+"，获取参数请求";
							returndata = Convert.hexStringToBytes(ProtocolsToClient.getTrue(ProtocolsToClient.GETPARAMATER, Convert.str2HexStr("ok")));
						}else {
							
						}
						break;
					default:
						logger.debug("未知信息,源码："+str);
						data = "IP:"+IP+"，未知信息src:"+str;
						break;
					}else if(src.contains("aa")&& src.contains("cc")) {
						String[] datas = src.split(",");
						if(datas.length > 3)
						switch (datas[2]) {
						case "01"://山洪灾害
							Torrent torrent = new Torrent();
							data = "山洪灾害数据获取:	IP:"+IP;
							for(int i = 3;i<datas.length;i++) {
								if(datas[i].equals("cc")) {
									break;
								}
								data += "	"+ProtocolsToClient.TORRENTDATA[(i-3)]+": "+datas[i];
							}
							torrent.setIMEI(ImeiMap.get(iPAddress));
							torrent.setIP(IP);
							torrent.setPow1(datas[4]);
							torrent.setGrouppow(datas[3]);
							torrent.setOutv1(datas[5]);
							torrent.setOutv2(datas[6]);
							torrent.setV24(datas[7]);
							torrent.setV28(datas[8]);
							torrent.setExtendpow(datas[9]);
							torrent.setSolarpow(datas[10]);
							torrent.setCharge(datas[11]);
							torrent.setWork(datas[12]);
							torrent.setTime(new Date());
							try {
								torrentservice.saveTorrent(torrent);
								logger.info("获取山洪灾害信息成功");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info(e.getMessage());
							}
							break;
						default:
							
							break;
						}else {
							logger.debug("未知信息,源码："+str);
							data = "IP:"+IP+"，未知信息src:"+str;
						}
					}else {
						logger.debug("未知信息,源码："+str);
						data = "IP:"+IP+"，未知信息src:"+str;
					}
					if(data != null && !data.equals("")) {
						Session mysession = new Session();
						mysession.setSessiondata(data);
						mysession.setSessiondate(new Date());
						mysession.setSessionuser(ProtocolsToClient.SESSIONCLIENT);
						try {
							sessionservice.saveSession(mysession);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// 向客户端写数据 
					// 组织IoBuffer数据包的方法：本方法才可以正确地让客户端UDP收到byte数组
					if(returndata != null) {
						IoBuffer buf = IoBuffer.wrap(returndata);  
			            WriteFuture future = session.write(buf);  
			            // 在100毫秒超时间内等待写完成
			            future.awaitUninterruptibly(100);
			            // The message has been written successfully
			            if( future.isWritten())
			            {
			                        // send sucess!
			            }
			            // The messsage couldn't be written out completely for some reason.
			            // (e.g. Connection is closed)
			            else
			            {
			                logger.warn("[IMCORE]回复给客户端的数据发送失败！");
			            }
					}
		            /*try {
						//logger.info("【NOTE】>>>>>> 收到客户端的数据："+new String(bs,"GB2312"));
			            //*********************************************** 回复数据
			            String strToClient = "Hello，我是Server，这是第"+(Number++)+"条回复消息我的时间戳是"+System.currentTimeMillis();
			            byte[] res;
						res = strToClient.getBytes("UTF-8");
						// 组织IoBuffer数据包的方法：本方法才可以正确地让客户端UDP收到byte数组
			            IoBuffer buf = IoBuffer.wrap(res);  
			                 
			            // 向客户端写数据
			            WriteFuture future = session.write(buf);  
			            // 在100毫秒超时间内等待写完成
			            future.awaitUninterruptibly(100);
			            // The message has been written successfully
			            if( future.isWritten())
			            {
			                        // send sucess!
			            }
			            // The messsage couldn't be written out completely for some reason.
			            // (e.g. Connection is closed)
			            else
			            {
			                logger.warn("[IMCORE]回复给客户端的数据发送失败！");
			            }
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				/*}
			});*/
    }
    @Override
	 public void messageSent(IoSession session, Object message) throws Exception {
    	//logger.info("------------服务端发消息到客户端---");
	 }
	 @Override
	 public void sessionClosed(IoSession session) throws Exception {
	  // TODO Auto-generated method stub
		 logger.info("远程session关闭了一个...");
	 }
	 @Override
	 public void sessionCreated(IoSession session) throws Exception {
	     //String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
	     //session.setAttribute("KEY_SESSION_CLIENT_IP", clientIP);
	     //logger.info("sessionCreated, client IP: " + clientIP);
	 }

	 //心跳检测触发接口，设置好心跳值后，如果规定时间内未有心跳，则调用此方法，常用于检测终端状态，断开连接等
	 @Override
	 public void sessionIdle(IoSession session, IdleStatus status)
	   throws Exception {
		 //logger.info(session.getServiceAddress() +"IDS");
	 }
	 @Override
	 public void sessionOpened(IoSession session) throws Exception {
		 //logger.info("连接打开："+session.getLocalAddress());
	 }
}
