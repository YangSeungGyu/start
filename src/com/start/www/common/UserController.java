package com.start.www.common;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.start.www.common.service.UserService;

import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	private CommonService commonService;

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Inject
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	//약관 동의
	@RequestMapping(value = "/common/joinContract.do ", method = RequestMethod.GET)
	public String joinContract(Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		if(session.getAttribute("userId") != null){
			//세션이있으면 홈으로
			return "start/home";
		} else{
			HashMap<String,String> contractTxtMap = commonService.joinContract(request);
	    	
	    	model.addAttribute("result", contractTxtMap );
			return "common/joinContract";
		}
	}
	
	
	//회원가입 정보 입력
	@RequestMapping(value = "/common/joinUserInfo.do ", method = {RequestMethod.POST,RequestMethod.GET})
	public String joinUserInfo(@ModelAttribute("paramBean") ParamBean param,Locale locale, Model model, HttpServletRequest request) throws Exception{
		//질문 리스트
		Map<String,Object> compMap = new HashMap<String,Object>();
		
		List<Object> pwQCompList = commonService.pwQCompList();
		List<Object> jobCompList = commonService.jobCompList();
		List<Object> joinPathCompList = commonService.joinPathCompList();
		List<Object> showInfoCompList = commonService.showInfoCompList();
		
		compMap.put("pwQCompList", pwQCompList);
		compMap.put("jobCompList", jobCompList);
		compMap.put("joinPathCompList", joinPathCompList);
		compMap.put("showInfoCompList", showInfoCompList);
		
		
		model.addAttribute("compMap", compMap);
		model.addAttribute("snsReceiveYn", param.getParamIsAdvertis());
		if(param.getParamIsAdvertis() == null){
			return "redirect:/common/joinContract.do";	
		}else{
			return "common/joinUserInfo";
		}
		
	}
	
	//아이디 체크
	@RequestMapping(value = "/common/checkId.do ", method = RequestMethod.POST)
	public void checkId(@ModelAttribute("paramBean") ParamBean param, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int checkId = userService.checkId(param);
		System.out.println(checkId);
		boolean check = checkId == 0 ? true : false; 
		System.out.println(check);
		JSONObject json=new JSONObject();
		json.put("check", check);
	 	response.setContentType("application/json; charset=utf-8");
	 	PrintWriter out=response.getWriter();
	 	out.print(json.toString());
		
	}
	
	//회원가입 액션
		@RequestMapping(value = "/common/joinUserAction.do ", method = RequestMethod.POST)
		public void joinUserAction(@ModelAttribute("userBean") UserBean param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
			//접근 로그 기록
			commonService.logAction(param.getUserId(),"J","800003",request);
			
			int isSuccess =userService.joinUserAction(param);
			boolean isSucess = false;;
			if(isSuccess != 0){
				isSucess = true;
			}else{
				isSucess = false;
			}
			
			JSONObject json=new JSONObject();
			json.put("isSucess", isSucess);
		 	response.setContentType("application/json; charset=utf-8");
		 	PrintWriter out=response.getWriter();
		 	out.print(json.toString());
			
		}
		
		//로그인 페이지
		@RequestMapping(value = "/common/login.do ", method = RequestMethod.GET)
		public String login(Locale locale, Model model, HttpServletRequest request) throws Exception{
			
			return "common/login";
		}
		
		//로그인 
		@RequestMapping(value = "/common/loginAction.do ", method = RequestMethod.POST)
		public void loginAction(@ModelAttribute("userBean") UserBean param, Model model, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
			String msg="";
			Boolean isSucess = false;
			/* resultType
			 * N : 사용자 없음
			 * S : 정지된 계정
			 * L : 패스워드5회 이상 틀려 잠긴 계정
			 * F : 패스워드 틀림
			 * T : 로그인 성공
			 */
			String resultType = userService.loginAction(param,session);
			
			if(resultType.equals("N")){
				msg="해당 계정이 존재하지 않습니다.";
			} else if(resultType.equals("s")){
				msg="해당 계정은 정지된 계정입니다. 관리자에게 문의해 주시기 바랍니다.";
			} else if(resultType.equals("L")){
				msg="패스워드5회 이상 실패하여 계정이 잠겼습니다.";
			} else if(resultType.equals("F")){
				msg="패스워드가 다릅니다.";
			} else {
				//접근 로그 기록
				String userId = (String)session.getAttribute("userId");
				commonService.logAction(userId,"L",null,request);
				msg="로그인에 성공 하셧습니다.";
				isSucess = true;
			}
					
			JSONObject json=new JSONObject();
			json.put("isSucess", isSucess);
			json.put("msg", msg);
		 	response.setContentType("application/json; charset=utf-8");
		 	PrintWriter out=response.getWriter();
		 	out.print(json.toString());
		}
		
		//로그아웃
		@RequestMapping(value = "/common/logoutAction.do ", method = RequestMethod.GET)
		public String logoutAction(@ModelAttribute("userBean") UserBean param, Model model, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
			//접근 로그 기록
			String userId = (String)session.getAttribute("userId");
			commonService.logAction(userId,"O",null,request);
			userService.logoutAction(param,session);
			
			return "start/home";				
		}
}
