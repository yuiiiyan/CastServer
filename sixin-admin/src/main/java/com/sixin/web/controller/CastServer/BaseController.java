package com.sixin.web.controller.CastServer;

import com.sixin.entity.Page;
import com.sixin.util.Logger;
import com.sixin.util.PageData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Shuofang
 *	TODO 基本的控制器
 */
public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	// private static final long serialVersionUID = 6357869213649815390L;

	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到ModelAndView
	 * 
	 * @return
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	/**
	 * 得到分页列表的信息
	 * 
	 * @return
	 */
	public Page getPage() {
		return new Page();
	}

	/**
	 * 获取IP
	 * 
	 * @throws Exception
	 */
	public String getRemortIP() throws Exception {
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}
		return ip;
	}
	/**
	 * 
	 * @param logger
	 * @param interfaceName
	 */
	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	/**
	 * 
	 * @param logger
	 * TODO
	 * 2018年12月28日
	 */
	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}

}
