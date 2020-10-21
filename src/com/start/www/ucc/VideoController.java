package com.start.www.ucc;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.www.admin.bean.HomeMainInfoBean;
import com.start.www.admin.bean.LogBean;
import com.start.www.admin.service.AdminHomeService;
import com.start.www.board.bean.SimpleBoardBean;
import com.start.www.common.bean.UserBean;
import com.start.www.common.service.CommonService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class VideoController {
	
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	//private AdminHomeService adminHomeService;
	private CommonService commonService;
	
	@Inject
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	/*@Inject
	public void setAdminHomeService(AdminHomeService adminHomeService) {
		this.adminHomeService = adminHomeService;
	}*/
	
	
	//관리자 메인
	@RequestMapping(value = "/ucc/video/test.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String ucc(Locale locale, Model model,HttpSession session, HttpServletRequest request){
		
			
		//접근 로그 기록
		//commonService.logAction((String)session.getAttribute("userId"),"M","800004",request);

		return "ucc/video/test";
	}
	
	
	
	//test
	@RequestMapping(value = "/ucc/video/testThum.do", method = {RequestMethod.GET,RequestMethod.POST})
	public void testThum(Locale locale, Model model,HttpSession session, HttpServletRequest request){
		
		
		//VideoThumbnail

	}
	
}
