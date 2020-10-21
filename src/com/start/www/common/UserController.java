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

	//��� ����
	@RequestMapping(value = "/common/joinContract.do ", method = RequestMethod.GET)
	public String joinContract(Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		if(session.getAttribute("userId") != null){
			//������������ Ȩ����
			return "start/home";
		} else{
			HashMap<String,String> contractTxtMap = commonService.joinContract(request);
	    	
	    	model.addAttribute("result", contractTxtMap );
			return "common/joinContract";
		}
	}
	
	
	//ȸ������ ���� �Է�
	@RequestMapping(value = "/common/joinUserInfo.do ", method = {RequestMethod.POST,RequestMethod.GET})
	public String joinUserInfo(@ModelAttribute("paramBean") ParamBean param,Locale locale, Model model, HttpServletRequest request) throws Exception{
		//���� ����Ʈ
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
	
	//���̵� üũ
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
	
	//ȸ������ �׼�
		@RequestMapping(value = "/common/joinUserAction.do ", method = RequestMethod.POST)
		public void joinUserAction(@ModelAttribute("userBean") UserBean param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
			//���� �α� ���
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
		
		//�α��� ������
		@RequestMapping(value = "/common/login.do ", method = RequestMethod.GET)
		public String login(Locale locale, Model model, HttpServletRequest request) throws Exception{
			
			return "common/login";
		}
		
		//�α��� 
		@RequestMapping(value = "/common/loginAction.do ", method = RequestMethod.POST)
		public void loginAction(@ModelAttribute("userBean") UserBean param, Model model, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
			String msg="";
			Boolean isSucess = false;
			/* resultType
			 * N : ����� ����
			 * S : ������ ����
			 * L : �н�����5ȸ �̻� Ʋ�� ��� ����
			 * F : �н����� Ʋ��
			 * T : �α��� ����
			 */
			String resultType = userService.loginAction(param,session);
			
			if(resultType.equals("N")){
				msg="�ش� ������ �������� �ʽ��ϴ�.";
			} else if(resultType.equals("s")){
				msg="�ش� ������ ������ �����Դϴ�. �����ڿ��� ������ �ֽñ� �ٶ��ϴ�.";
			} else if(resultType.equals("L")){
				msg="�н�����5ȸ �̻� �����Ͽ� ������ �����ϴ�.";
			} else if(resultType.equals("F")){
				msg="�н����尡 �ٸ��ϴ�.";
			} else {
				//���� �α� ���
				String userId = (String)session.getAttribute("userId");
				commonService.logAction(userId,"L",null,request);
				msg="�α��ο� ���� �ϼ˽��ϴ�.";
				isSucess = true;
			}
					
			JSONObject json=new JSONObject();
			json.put("isSucess", isSucess);
			json.put("msg", msg);
		 	response.setContentType("application/json; charset=utf-8");
		 	PrintWriter out=response.getWriter();
		 	out.print(json.toString());
		}
		
		//�α׾ƿ�
		@RequestMapping(value = "/common/logoutAction.do ", method = RequestMethod.GET)
		public String logoutAction(@ModelAttribute("userBean") UserBean param, Model model, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
			//���� �α� ���
			String userId = (String)session.getAttribute("userId");
			commonService.logAction(userId,"O",null,request);
			userService.logoutAction(param,session);
			
			return "start/home";				
		}
}
