package com.sixin.util;
/**
 * 项目名称：
*/
public class Const {
	public static final String LOGIN = "/index.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET配置路径
	public static final String FILEPATH = "uploadFiles/";	//文件路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHPER = "uploadFiles/perfile/";	//节目文件夹
	public static final String CONFIG = "admin/config/config.properties";	//终端交互端口配置路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";	//不对匹配该值的访问路径拦截（正则）
		public static final String SESSION_FID = "FID";
		
		public static final String SESSION_FNAME = "FNAME";	
		
		public static final String[] LOGTYPE=new String[]{"登陆日志","操作日志"};
}
