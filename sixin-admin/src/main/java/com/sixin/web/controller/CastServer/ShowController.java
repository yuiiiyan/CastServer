/**
 * @Title:  ShowController.java
 * @Package com.shuofang.controller
 * @Description:    TODO(用一句话描述该文件做什么)
 * @author: Shuofang
 * @date:   2019年1月3日 上午10:39:11
 * @version V1.0
 * @Copyright: 2019
 */
package com.sixin.web.controller.CastServer;

import com.sixin.entity.*;
import com.sixin.server.ServerDataEntity;
import com.sixin.server.UnicastThread;
import com.sixin.service.ClientManager;
import com.sixin.service.LogManager;
import com.sixin.service.SessionManager;
import com.sixin.service.TorrentManager;
import com.sixin.util.Const;
import com.sixin.util.PageData;
import com.sixin.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author Shuofang
 *	TODO
 */
@Controller
@RequestMapping(value="/show")
public class ShowController extends BaseController{
	@Resource(name = "logService")
	private LogManager logService;
	@Resource(name = "sessionService")
	private SessionManager sessionService;
	@Resource(name = "clientService")
	private ClientManager clientService;
	@Resource(name = "torrentService")
	private TorrentManager torrentService;
	/**
	 * 查询所有任务
	 *
	 * @param @throws
	 *            Exception
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/index")
	public ModelAndView listUserlogs(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		String is = (null == pd.get("is") || "".equals(pd.get("is").toString()))?"":pd.get("is").toString();
		String result = (null == pd.get("result") || "".equals(pd.get("result").toString()))?"":pd.get("result").toString();
		/*if(is.equals("true") && !ClientLink.isConnected()) {
			ClientLink.StartLink();
		}else if(is.equals("false")&& ClientLink.isConnected()) {
			ClientLink.Close();
		}*/
		if(is.equals("true") && ServerDataEntity.getUnicastThread() == null) {
				logger.info("初始化监听");
				int port = Integer.parseInt(Tools.GetValueByKey(Const.CONFIG, "Port"));
				logger.info("获取端口"+port);
				/*MinaCastThread castThread = new MinaCastThread(port);
				logger.info("创建minaThread");*/
				UnicastThread unicastThread= new UnicastThread(port);
				ServerDataEntity.setExecutorService(Executors.newCachedThreadPool());//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
				logger.info("创建线程池");
				/*ServerDataEntity.setMinaCastThread(castThread);
				castThread.run();
				logger.info("minaThread运行");*/
				ServerDataEntity.setUnicastThread(unicastThread);
				unicastThread.start();
				logger.info("UnicastThread启动监听");
		}
		List<PageData> fingerLogs = logService.getUserLogs(page);
		PageData passData = logService.getUserPassLogsNum(page);
		mv.addObject("pd", pd);
		mv.addObject("isconnect", "false");
		mv.addObject("pass", passData.get("passCount"));
		mv.addObject("nopass", passData.get("noPassCount"));
		mv.addObject("fingerLogs", fingerLogs);
		mv.setViewName("index/list");
		return mv;
	}

	/**
	 * 查询所有日志
	 *
	 * @param @throws
	 *            Exception
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/logs")
	public ModelAndView listAllLogs(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<Log> Logs = logService.getAllLogs(page);
		mv.addObject("Logs", Logs);
		mv.setViewName("index/logs");
		return mv;
	}
	/**
	 * 查询所有会话
	 *
	 * @param @throws
	 *            Exception
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/sessions")
	public ModelAndView listAllsessions(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<Session> sessions = sessionService.getAllSessions(page);
		mv.addObject("Session", sessions);
		mv.setViewName("index/session");
		return mv;
	}

	/**
	 * 显示指纹用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/listfinger")
	public ModelAndView listfingers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			List<Client> fingers = new ArrayList<Client>();
			page.setPd(pd);
			fingers = clientService.getAllUser(page);
			mv.addObject("pd", pd);
			mv.addObject("fingers", fingers);
			mv.setViewName("index/finger");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 请求新增用户页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addfingers")
	public ModelAndView addfingers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			mv.addObject("MSG", "add");
			mv.addObject("pd", pd);
			mv.setViewName("index/addfinger");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 请求新增用户页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/editfingers")
	public ModelAndView editfingers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			mv.addObject("MSG", "edit");
			mv.addObject("pd", pd);
			mv.setViewName("index/addfinger");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 同步绑定指纹信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/usefinger")
	public ModelAndView usefingers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		Client fPrint = new Client();
		//fPrint.setFingerid(Integer.parseInt(pd.get("fingerid").toString()));
		try{
			/*ClientLink.SendData(Protocol.Start+Protocol.ADD_Finger+pd.get("fingerid").toString()+Protocol.END);
			errInfo = "infoUse";
			Session session = new Session();
			session.setSessiondata("发送添加指纹信息，指纹ID："+pd.get("fingerid").toString());
			session.setSessiondate(new Date());
			session.setSessionuser(Protocol.SESSIONCLIENT);
			sessionService.saveSession(session);
			//插入日志
			logService.saveLog(pd.get("fingerid").toString(),Protocol.LOGTYPE[1],"同步指纹",pd.get("fingerid").toString());*/
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.addObject("result", errInfo);
		mv.setViewName("redirect:index.do?result="+errInfo); // 保存成功跳转到列表页面
		return mv;
	}
	/**
	 * 添加任务信息
	 *
	 * @param term
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(Client fingerPrint) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			/*fingerPrint.setIsuse(false);
			fingerPrint.setCreatdate(new Date());
			fingerPrintService.saveUser(fingerPrint);// 保存任务
*/			// 插入日志
			/*logService.saveLog(null,Protocol.LOGTYPE[1], "新增用户", fingerPrint.getUsername());*/
		} catch (Exception e) {
			logger.error(e.toString(), e);
			mv.addObject("msg", "failed");
		}
		mv.setViewName("redirect:index.do"); // 保存成功跳转到列表页面
		return mv;
	}
	/**
	 * 添加任务信息
	 *
	 * @param term
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(Client fingerPrint) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			/*fingerPrint.setIsuse(false);
			fingerPrint.setCreatdate(new Date());
			fingerPrintService.editUser(fingerPrint);// 保存任务
			// 插入日志
*/			/*logService.saveLog(null,Protocol.LOGTYPE[1], "修改用户", fingerPrint.getUsername());*/
		} catch (Exception e) {
			logger.error(e.toString(), e);
			mv.addObject("msg", "failed");
		}
		mv.setViewName("redirect:index.do"); // 保存成功跳转到列表页面
		return mv;
	}
	/**
	 * 删除终端
	 * @param TID
	 * @param out
	 */
	@RequestMapping(value="/deletefinger")
	@ResponseBody
	public ModelAndView delete()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		try{
			/*ClientLink.SendData(Protocol.Start+Protocol.Del_Finger+pd.get("fingerid").toString()+Protocol.END);
			fingerPrintService.DeleteUser(pd.get("fingerid").toString());
			errInfo = "success";
			Session session = new Session();
			session.setSessiondata("发送删除指纹信息，指纹ID："+pd.get("fingerid").toString());
			session.setSessiondate(new Date());
			session.setSessionuser(Protocol.SESSIONCLIENT);
			sessionService.saveSession(session);
			//插入日志
			logService.saveLog(null,Protocol.LOGTYPE[1],"删除",pd.get("fingerid").toString());*/
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.addObject("result", errInfo);
		mv.setViewName("redirect:index.do?result="+errInfo); // 保存成功跳转到列表页面
		return mv;
	}
	/**
	 * 显示采集数据列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/listtorrent")
	public ModelAndView listtorrent(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			List<Torrent> torrents = new ArrayList<Torrent>();
			page.setPd(pd);
			torrents = torrentService.getAllTorrents(page);
			mv.addObject("pd", pd);
			mv.addObject("torrents", torrents);
			mv.setViewName("index/torrents");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
}
