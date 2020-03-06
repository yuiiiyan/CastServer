/**  
 * @Title:  MinaTcpClient.java   
 * @Package testpro   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月19日 下午2:49:54   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.server;

/**
 * @author Shuofang
 *	TODO
 */

import com.sixin.util.Convert;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @version 1.0
 * @since
 */
public class MinaTcpClient extends IoHandlerAdapter {
	private IoConnector connector;
	private static IoSession session;
	public MinaTcpClient() {
		connector = new NioSocketConnector();
		connector.setHandler(this);
		ConnectFuture connFuture = connector.connect(new InetSocketAddress(/*"110.53.162.164",*/8900));
		connFuture.awaitUninterruptibly();
		session = connFuture.getSession();
		System.out.println("TCP 客户端启动");
	}
	public static void main(String[] args) throws Exception {
		MinaTcpClient client = new MinaTcpClient();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String start = "AA11110001";
				//String data = "12345678,107.3";
				String data = "123456789123450";
				String end = "1ECC";
				String data1 = Convert.str2HexStr(data);
				System.out.println(data1);
				data = start+data1+end;
				System.out.println(data);
				byte[] bts = Convert.hexStringToBytes(data);
				IoBuffer buffer = IoBuffer.allocate(bts.length);
				// 自动扩容
				buffer.setAutoExpand(true);
				// 自动收缩
				buffer.setAutoShrink(true);
				buffer.put(bts);
				buffer.flip();
				session.write(buffer);
			}
		}, 0, 60000);
		// 关闭会话，待所有线程处理结束后
		//client.connector.dispose(true);
	}
	@Override
	public void messageReceived(IoSession iosession, Object message)
			throws Exception {
		IoBuffer bbuf = (IoBuffer) message;
		byte[] byten = new byte[bbuf.limit()];
		bbuf.get(byten, bbuf.position(), bbuf.limit());
		System.out.println("客户端收到消息:" + Convert.bytesToHexString(byten));
		String oString = "ok";
		byte[] ok = new byte[2];
		System.arraycopy(byten,5,ok,0,2);
		if(byten[1] == 17)  {
			if(oString.equals(new String(ok))) {
				byte[] bts = Convert.hexStringToBytes("61612c31322c30312c32342e33312c31322e30322c302e30302c302e30302c32342e30362c32332e39382c302e30302c32312e38332c30312c322c6363");//模拟终端信息
				IoBuffer buffer = IoBuffer.allocate(bts.length);
				// 自动扩容
				buffer.setAutoExpand(true);
				// 自动收缩
				buffer.setAutoShrink(true);
				buffer.put(bts);
				buffer.flip();
				session.write(buffer);
				System.out.println("上传终端信息");
			}
		}
		/*if(Convert.bytesToHexString(byten).equals("aa111100016f6b1ecc")) {
			byte[] bts = "AA,12,01,22.51,0.00,0.00,0.00,22.26,22.28,0.02,0.02,01,255,CC".getBytes();
			IoBuffer buffer = IoBuffer.allocate(bts.length);
			// 自动扩容
			buffer.setAutoExpand(true);
			// 自动收缩
			buffer.setAutoShrink(true);
			buffer.put(bts);
			buffer.flip();
			session.write(buffer);
			System.out.println("上传终端信息");
		}*/
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("客户端异常");
		super.exceptionCaught(session, cause);
	}
	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
		System.out.println("客户端消息发送");
		super.messageSent(iosession, obj);
	}
	@Override
	public void sessionClosed(IoSession iosession) throws Exception {
		System.out.println("客户端会话关闭");
		super.sessionClosed(iosession);
	}
	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		System.out.println("客户端会话创建");
		super.sessionCreated(iosession);
	}
	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus)
			throws Exception {
		System.out.println("客户端会话休眠");
		super.sessionIdle(iosession, idlestatus);
	}
	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		System.out.println("客户端会话打开");
		super.sessionOpened(iosession);
	}
}