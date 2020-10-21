package com.start.www.admin;

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
public class AdminHomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminHomeController.class);
	
	private AdminHomeService adminHomeService;
	private CommonService commonService;
	
	@Inject
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	@Inject
	public void setAdminHomeService(AdminHomeService adminHomeService) {
		this.adminHomeService = adminHomeService;
	}
	
	
	//관리자 메인
	@RequestMapping(value = "/admin/main.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String main(Locale locale, Model model,HttpSession session, HttpServletRequest request){
		
		
		String userType = (String)session.getAttribute("userType");
		if(userType==null || userType.equals("U")){
		
			model.addAttribute("redirectUrl", "/start/home.do");
			model.addAttribute("redirectMsg", "잘못된 접근 입니다..");
			return "forward:/common/redirectMsg.do";
			
		}
		//접근 로그 기록
		commonService.logAction((String)session.getAttribute("userId"),"M","800004",request);
		
		HomeMainInfoBean mainInfo = adminHomeService.mainInfo();
		model.addAttribute("result", mainInfo);

		return "admin/main";
	}
	
	//로그 확인 피에지
	@RequestMapping(value = "/admin/log.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String log(@ModelAttribute("logBean") LogBean param,Locale locale, Model model,HttpSession session, HttpServletRequest request){
			
		String userType = (String)session.getAttribute("userType");
		if(userType==null || userType.equals("U")){
			
			model.addAttribute("redirectUrl", "/start/home.do");
			model.addAttribute("redirectMsg", "잘못된 접근 입니다..");
			return "forward:/common/redirectMsg.do";
				
		}
		//접근 로그 기록
		commonService.logAction((String)session.getAttribute("userId"),"M","800005",request);
			
		int crntPage = 1; //현제페이지
		if(param.getCrntPage() != 0){
			crntPage = param.getCrntPage(); 
		}
			
		int maxRow = 15; //출력 row수
		double maxPage = 10.0; //출력 page 개수
		int maxRowCnt = adminHomeService.getMaxCnt(); //게시물 최대수
		int maxPageCnt = (int)Math.ceil(maxRowCnt/(double)maxRow); //page 최대 수
			
		//검색조건 범위
		int firstRow = ((crntPage * maxRow)-maxRow)+1;  
		int lastRow = ((crntPage * maxRow)); 
			
		boolean isPrewPage = false;
		boolean isNextPage = false;
			
			
		//페이지 출력 범위
		int firstPage = (int)(Math.ceil(crntPage/(maxPage))*maxPage-maxPage)+1;
		int lastPage = (int)(Math.ceil(crntPage/(maxPage))*maxPage);
		if(lastPage>maxPageCnt){
			lastPage = maxPageCnt;
		}
		
		if(firstPage != 1){
			isPrewPage = true;
		}
		if(lastPage!=maxPageCnt){
			isNextPage = true;
		}
			
		param.setFirstRow(firstRow);
		param.setLastRow(lastRow);
		param.setFirstPage(firstPage);
		param.setLastPage(lastPage);
		param.setIsPrewPage(isPrewPage);
		param.setIsNextPage(isNextPage);
		param.setMaxPageCnt(maxPageCnt);
			
		List<Object> logInfo = adminHomeService.logInfo(param);
		model.addAttribute("pageInfo", param);
		model.addAttribute("result", logInfo);
		return "admin/log";
	}
	
		
	//로그 엑셀 
	@RequestMapping(value = "/admin/logExcel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String logExcel(Locale locale, Model model,HttpSession session, HttpServletRequest request){
			
		 LogBean param = new LogBean(); 
		//본인에맞는 데이터 가져온다
		  List<Object> plist = adminHomeService.logInfo(param); 
		    
		//색칠된부분은 필수!!!
		  HashMap<String,Object> row = new HashMap<String, Object>();
		  HashMap<String,String> cell = null;
		  List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		  
		//본인의 데이터 가져와서 루프돌려서 cell 맵에 담아요
		 if (plist != null && plist.size() > 0) {
		   int rowName = 1;
		   for (int i = 0; i < plist.size(); i++) {
			LogBean data = (LogBean)plist.get(i);
		    cell = new HashMap<String, String>();
		    
		    cell.put("row"+rowName+"_cell1", data.getUserId());
		    cell.put("row"+rowName+"_cell2", data.getUserNicNm());
		    cell.put("row"+rowName+"_cell3", data.getUserNm());
		    cell.put("row"+rowName+"_cell4", data.getUserTypeNm());
		    cell.put("row"+rowName+"_cell5", data.getAccessNm());
		    cell.put("row"+rowName+"_cell6", data.getAccessIp());
		    cell.put("row"+rowName+"_cell7", data.getAccessBrwsr());
		    cell.put("row"+rowName+"_cell8", data.getAccessDt());
		        
		    list.add(cell);
		    rowName++;
		   }
		  }
		  row.put("dataList", list);//데이터목록
		  row.put("dataLength", 7);//엑셀셀갯수
		  
		  String labels[] = { "ID", "닉네임", "이름", "유저타입", "로그내용", "접속IP", "BROWSER", "접근시간"};//셀타이틀 
		  int columnWidth[] = { 10, 15, 10, 10, 20, 20, 10, 20};//셀크기 가로
		  
		  model.addAttribute("fileName", "log.xlsx");//시트명 
		  model.addAttribute("columnWidth", columnWidth);
		  model.addAttribute("sheetName", "로그");//시트명
		  model.addAttribute("labels", labels);//셀타이틀 
		  model.addAttribute("dataMap", row);
		  
		  return "excelXlsx";
		}
	
	
	
	//관리자 메인
		@RequestMapping(value = "/admin/code.do", method = {RequestMethod.GET,RequestMethod.POST})
		public String code(Locale locale, Model model,HttpSession session, HttpServletRequest request){
			
			
			String userType = (String)session.getAttribute("userType");
			if(userType==null || userType.equals("U")){
			
				model.addAttribute("redirectUrl", "/start/home.do");
				model.addAttribute("redirectMsg", "잘못된 접근 입니다..");
				return "forward:/common/redirectMsg.do";
				
			}
			//접근 로그 기록
			commonService.logAction((String)session.getAttribute("userId"),"M","800004",request);
			
			//HomeMainInfoBean mainInfo = adminHomeService.mainInfo();
			//model.addAttribute("result", mainInfo);

			return "admin/code";
		}
}
