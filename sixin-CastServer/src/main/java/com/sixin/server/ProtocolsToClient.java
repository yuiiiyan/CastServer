/**  
 * @Title:  ProtocolsToClient.java   
 * @Package com.shuofang.server   
 * @Description:    TODO(终端服务器交互指令解析与打包)   
 * @author: Shuofang     
 * @date:   2019年1月18日 下午2:35:55   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.server;

import com.sixin.util.Convert;

/**
 * @author Shuofang
 *	TODO 
 */
public class ProtocolsToClient {
	public static final String PACKETHEAD = "aa";//包头
	public static final String E_FM_RECIVE = "0a";//应急FM调频接收RDS码/接收频率
	public static final String CLIENTREAD = "0b";//获取终端RDS码/发射频率/发射功率/反射功率/工作电压（对系统只读）
	public static final String IPCHANGE = "0c";//获取终端IP（对系统可读可写）并为流媒体心跳包
	public static final String CELLPOS = "0d";//基站信息（LAC,CID）
	public static final String OPENSTREAM = "0e";//启动流媒体传输
	public static final String CLOSESTREAM = "0f";//停止流媒体传输
	public static final String UPDATELED = "10";//LED字幕更新（终端主动请求获取）
	public static final String GETPARAMATER = "11";//物联网终端参数透传接口（终端主动请求获取，端口:8900）
	public static final String GETCLIENTDATA = "12";//物联网终端参数透传接口 终端回传参数
	public static final String COMMAND = "1";//命令正确
	public static final String READLENGTH = "0a00";///获取终端RDS码/发射频率/发射功率/反射功率/工作电压（对系统只读）命令长度
	public static final String CHECKCODE = "1e";//固定校验码END CHECK
	public static final String ENDCHECK = "cc";//包尾
	public static final int SESSIONUSER = 0;//终端为客机
	public static final int SESSIONCLIENT = 1;//服务器为主机
	public static final String[] TORRENTDATA = {
			"电池组电压",
			"电池1电压",
			"18V1输出电压",
			"18V2输出电压",
			"24V输出电压",
			"28V输出电压",
			"外部电源电压",
			"太阳能电压",
			"充电状态",
			"工作状态"
	};
	public static String getTrue(String type,String data) {//返回确认命令
		if(data == null)
			return PACKETHEAD+type+"0200"+"01"+CHECKCODE+ENDCHECK;
		else if(data.equals(Convert.str2HexStr("ok"))){
			return PACKETHEAD+type+"1100"+"01"+data+CHECKCODE+ENDCHECK;
			/*else
				return PACKETHEAD+type+(int)(data.length()/32)+(int)(data.length()%32/2+2)+(int)(data.length()/256/16)+(int)(data.length()/256%16)+"01"+data+CHECKCODE+ENDCHECK;*/
		}else {
			if(data.length() < 512)
				return PACKETHEAD+type+(data.length()/32)+""+(data.length()%32/2+2)+"00"+"01"+data+CHECKCODE+ENDCHECK;
		}
		return null;
	}
}
