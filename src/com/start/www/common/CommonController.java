package com.start.www.common;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.www.common.bean.ParamBean;
import com.start.www.common.bean.UserBean;
import com.start.www.common.service.CommonService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	private CommonService commonService;

	@Inject
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	
	//메시지 출력 후 리다이렉트
	@RequestMapping(value = "/common/redirectMsg.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String redirectMsg(HttpServletRequest request,Locale locale, Model model){
		
		model.addAttribute("redirectUrl", (String)request.getAttribute("redirectUrl"));
		model.addAttribute("redirectMsg", (String)request.getAttribute("redirectMsg"));
		
		return "common/redirectMsg";
	}
	
	
}
