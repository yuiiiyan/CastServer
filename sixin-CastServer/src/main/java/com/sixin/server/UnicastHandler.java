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
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @author Shuofang
 *	TODO
 */
public class UnicastHandler extends ChannelInboundHandlerAdapter{
	private ByteBuf firstMessage;
	private Logger logger = LoggerFactory.getLogger(UnicastHandler.class);
    private SessionManager sessionservice = (SessionService) SpringContextUtils.getBeanByClass(SessionService.class);
    private TorrentManager torrentservice = (TorrentService) SpringContextUtils.getBeanByClass(TorrentService.class);
    private Map<InetSocketAddress, String> ImeiMap = ServerDataEntity.getIMEI_IP();
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		/*byte[] data = "服务器，给我一个APPLE".getBytes();
		firstMessage=Unpooled.buffer();
		firstMessage.writeBytes(data);
		ctx.writeAndFlush(firstMessage);*/
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
	throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] content = new byte[buf.readableBytes()];
		buf.readBytes(content);
		//channelActive(ctx);
		/*System.out.println("客户端收到服务器数据:");
		for(byte i:data) {
			System.out.print(i);
		}
		System.out.println();
		String Str_16 = Convert.bytesToHexString(data);
		System.out.println(Str_16);*/
		ServerDataEntity.putThreadintoPool(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				byte[] returndata = null;
				String str = null;
				str = Convert.bytesToHexString(content);
				String src = new String(content);
				InetSocketAddress iPAddress = (InetSocketAddress)ctx.channel().remoteAddress();
				String IP  =  iPAddress.getAddress().getHostAddress();
				String data = "";
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
						if(ImeiMap.get(iPAddress) == null || !new String(IMEI).equals(ImeiMap.get(iPAddress)))
							ImeiMap.put(iPAddress, new String(IMEI));
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
						if(ImeiMap.get(iPAddress) == null || !new String(IMEI).equals(ImeiMap.get(iPAddress)))
							ImeiMap.put(iPAddress, new String(IMEI));
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
						data = "山洪灾害数据;IP:"+IP;
						for(int i = 3;i<datas.length;i++) {
							if(datas[i].equals("cc")) {
								break;
							}
							data += ";"+ ProtocolsToClient.TORRENTDATA[(i-3)]+":"+datas[i];
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
							//logger.info("获取山洪灾害信息成功");
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
				if(returndata != null) {
					firstMessage=Unpooled.buffer();
					firstMessage.writeBytes(returndata);
					ctx.writeAndFlush(firstMessage);
				}
			}
		});
	}
}
