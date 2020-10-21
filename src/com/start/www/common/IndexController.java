package com.start.www.common;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.www.common.service.CommonService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	private CommonService commonService;

	@Inject
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	

	

	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		
		String formattedDate = dateFormat.format(date);
		
		
		model.addAttribute("serverTime", formattedDate );
		
		return "test/test";
	}
	
	@RequestMapping(value = {"/start/home","/"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "start/home";
	}
	
	@RequestMapping(value = "/include/tag", method = RequestMethod.GET)
	public String includeTag(Locale locale, Model model){
		
		return "include/tag";
	}
	
	
}
