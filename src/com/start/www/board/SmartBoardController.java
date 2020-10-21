package com.start.www.board;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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

import com.start.www.board.bean.ImageEditor;
import com.start.www.board.bean.SmartBoardBean;
import com.start.www.board.bean.SmartCommentBean;
import com.start.www.board.bean.SmartFileBean;

import java.util.Map;
import java.util.UUID;

import com.start.www.board.service.SmartBoardService;
import com.start.www.common.service.CommonService;

import net.sf.json.JSONObject;


/**
 * Handles requests for the application home page.
 */

@Controller
public class SmartBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SmartBoardController.class);
	
	private SmartBoardService smartBoardService;
	private CommonService commonService;
	

	@Inject
	public void setSmartBoardService(SmartBoardService smartBoardService) {
		this.smartBoardService = smartBoardService;
	}
	@Inject
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	//占쏙옙占쏙옙트 占쌉쏙옙占쏙옙 占쏙옙占쏙옙트
	@RequestMapping(value = "/board/smartBoard/smartList.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String smartList(@ModelAttribute("smartBoardBean") SmartBoardBean param,Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//占쏙옙占쏙옙 占싸깍옙 占쏙옙占�
		commonService.logAction((String)session.getAttribute("userId"),"M","800002",request);	
		
		
		String pageCategory = param.getPageCategory();
		if(pageCategory != null){
			session.setAttribute("pageCategory", pageCategory);
		}else{
			pageCategory = (String)session.getAttribute("pageCategory");
			param.setPageCategory(pageCategory);
		}
		String boardNm = smartBoardService.getBoardNm(param); //占쌉쏙옙占쏙옙 占싱몌옙
		
		int crntPage = 1; //占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
		if(param.getCrntPage() != 0){
			crntPage = param.getCrntPage(); 
		}
		
		int maxRow = 10; //占쏙옙占� row占쏙옙
		double maxPage = 5.0; //占쏙옙占� page 占쏙옙占쏙옙
		int maxRowCnt = smartBoardService.getMaxCnt(param); //占쌉시뱄옙 占쌍댐옙占�
		int maxPageCnt = (int)Math.ceil(maxRowCnt/(double)maxRow); //page 占쌍댐옙 占쏙옙
		
		//占싯삼옙占쏙옙占쏙옙 占쏙옙占쏙옙
		int firstRow = ((crntPage * maxRow)-maxRow)+1;  
		int lastRow = ((crntPage * maxRow)); 
		
		
		boolean isPrewPage = false;
		boolean isNextPage = false;
		
		
		//占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙
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
		
		String userId = (String)session.getAttribute("userId");
		String userType = (String)session.getAttribute("userType");
		
		param.setUserId(userId);
		param.setUserType(userType);
		List<Object> smartBoardList = smartBoardService.smartList(param);
		
		model.addAttribute("boardNm", boardNm);
		model.addAttribute("pageInfo", param);
		model.addAttribute("result", smartBoardList);
		
		return "board/smartBoard/smartList";
	}
	
	//占쏙옙占쏙옙트 占쌉쏙옙占쏙옙 占쌜쇽옙 占쏙옙占쏙옙占쏙옙
	@RequestMapping(value = "/board/smartBoard/smartWrite.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String smartWrite(@ModelAttribute("smartBoardBean") SmartBoardBean param, Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		boolean isLogin = commonService.checkLogin(session);
		
		if(!isLogin){
			model.addAttribute("redirectUrl", "/board/smartBoard/smartList.do");
			model.addAttribute("redirectMsg", "占싸깍옙占쏙옙 占쏙옙 占쏙옙占� 占싹쏙옙 占쏙옙 占쌍쏙옙占싹댐옙.");
			return "forward:/common/redirectMsg.do";
		}else{
			 List<Object> categoryList = smartBoardService.smartCategoryList();
			
			model.addAttribute("reParam", param);
			model.addAttribute("categoryList", categoryList);
			return "board/smartBoard/smartWrite";
		}
	}
	
	//占쏙옙占쏙옙트 占쌉쏙옙占쏙옙 占쌜쇽옙
	@RequestMapping(value = "/board/smartBoard/smartWriteAction.do", method = RequestMethod.POST)
	public String smartWriteAction(@ModelAttribute("smartFileBean")SmartFileBean fileBean
			,@ModelAttribute("smartBoardBean") SmartBoardBean param, Model model
			, HttpServletRequest request, HttpSession session) throws Exception{
		//占쏙옙占쏙옙 占싸깍옙 占쏙옙占�
		commonService.logAction((String)session.getAttribute("userId"),"I","800002",request);	
		String userId = (String)session.getAttribute("userId");
		String userNicNm = (String)session.getAttribute("userNicNm");
			
		param.setUserId(userId);
		param.setUserNicNm(userNicNm);
		
		int isSucess = smartBoardService.smartWriteAction(param,fileBean);
				
		model.addAttribute("redirectUrl", "/board/smartBoard/smartList.do");
		model.addAttribute("redirectMsg", "占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占� 占실억옙占쏙옙占싹댐옙.");
		return "forward:/common/redirectMsg.do";
	}

	
	//占쏙옙占쏙옙트 占쌉쏙옙占쏙옙 占쏙옙
	@RequestMapping(value = "/board/smartBoard/smartRead.do", method = RequestMethod.GET)
	public String smartRead(@ModelAttribute("smartBoardBean") SmartBoardBean param,Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
						
		int isSucess = smartBoardService.addCnt(param);
		boolean isModify = false;
		SmartBoardBean smartBoardRead = smartBoardService.smartRead(param);
		List<SmartBoardBean> smartFileList = smartBoardService.smartFileList(param);
		
		List<SmartCommentBean> smartCommentList = smartBoardService.smartCommentList(param);
		
		String userId = (String)session.getAttribute("userId");
		String userType = (String)session.getAttribute("userType");		
		if(userId != null && !userType.equals("U")){
			isModify = true;
		}else if(userId != null && userId.equals(smartBoardRead.getUserId())){
			isModify = true;
		}
		
		
		model.addAttribute("result", smartBoardRead);
		model.addAttribute("fileList", smartFileList);
		model.addAttribute("commentList", smartCommentList);
		
		model.addAttribute("isModify", isModify);
		return "board/smartBoard/smartRead";
	}
	
	//占쏙옙占쏙옙 占쌕울옙琯占�
		@RequestMapping(value = "/board/smartBoard/smartFileDownLoad.do")
		public String smartFileDownLoad(@ModelAttribute("smartFileBean")SmartFileBean fileBean, Model model) throws Exception{
			String path = "C:/lottery/file/";
			
			String fileNm = fileBean.getFileNm();
			String fullPath = path + fileBean.getFileId();
			File file = new File(fullPath);
			
			
			model.addAttribute("fileName", fileNm);
			model.addAttribute("file", file);
			return "FileDownload";
		}

	
	//占쏙옙占쏙옙트 占쌉쏙옙占쏙옙 占쏙옙占쏙옙
	@RequestMapping(value = "/board/smartBoard/smartDeleteAction.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String smartDeleteAction(@ModelAttribute("smartBoardBean") SmartBoardBean param,Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		// , HttpSession session
		//占쏙옙占쏙옙 占싸깍옙 占쏙옙占�
		commonService.logAction((String)session.getAttribute("userId"),"D","800002",request);	
		
		int isSucess = smartBoardService.smartDeleteAction(param);
		
		model.addAttribute("redirectUrl", "/board/smartBoard/smartList.do");
		model.addAttribute("redirectMsg", "占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占실억옙占쏙옙占싹댐옙.");
		return "forward:/common/redirectMsg.do";
	}		
			
	//占쏙옙占쏙옙트 占쌉쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
	@RequestMapping(value = "/board/smartBoard/smartModify.do", method = RequestMethod.POST)
	public String smartModify(@ModelAttribute("smartBoardBean") SmartBoardBean param, Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		List<Object> categoryList = smartBoardService.smartCategoryList();
		//占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙
		List<SmartBoardBean> SmartFileRead = smartBoardService.smartFileList(param);
		//占쏙옙占쏙옙 占쌩곤옙 max占쏙옙 占쏙옙占�
		String fileLength = SmartFileRead.size()+"";
		param.setFileCnt(fileLength);
		
		model.addAttribute("categoryList", categoryList);
		
		model.addAttribute("fileList", SmartFileRead);
		model.addAttribute("result", param);
		return "board/smartBoard/smartModify";
	}
	
	//占쏙옙占쏙옙트 占쌉쏙옙占쏙옙 占쏙옙占쏙옙
	@RequestMapping(value = "/board/smartBoard/smartModifyAction.do", method = RequestMethod.POST)
	public String smartModifyAction(@ModelAttribute("smartFileBean")SmartFileBean fileBean
			,@ModelAttribute("smartBoardBean") SmartBoardBean param
			, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		//占쏙옙占쏙옙 占싸깍옙 占쏙옙占�
		commonService.logAction((String)session.getAttribute("userId"),"U","800002",request);
		
		String upUserId = (String)session.getAttribute("userId");
		param.setUpUserId(upUserId);			
		
		int isSucess = smartBoardService.smartModifyAction(param,fileBean);
						
		model.addAttribute("redirectUrl", "/board/smartBoard/smartList.do");
		model.addAttribute("redirectMsg", "占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占실억옙占쏙옙占싹댐옙.");
		return "forward:/common/redirectMsg.do";
	}
	
	
	//占쌉시뱄옙 占쏙옙천
	@RequestMapping(value = "/board/smartBoard/smartRcmndCnt.do", method = RequestMethod.POST)
    public void smartRcmndCnt(@ModelAttribute("smartBoardBean") SmartBoardBean param, HttpServletResponse response, HttpSession session) throws Exception {
		String userId = (String)session.getAttribute("userId");
		param.setUserId(userId);
		Map <String,Object> result = smartBoardService.smartRcmndCnt(param);
		 
        JSONObject jso=new JSONObject();
 		jso.put("msg", result.get("msg"));
         
 		response.setContentType("application/json; charset=utf-8");
 		PrintWriter out=response.getWriter();
 		out.print(jso.toString());
    } 
	
	
	//占쌘몌옙트 占쌜쇽옙
	@RequestMapping(value = "/board/smartBoard/smartCommentWriteAction.do", method = RequestMethod.POST)
	public void smartCommentWriteAction(@ModelAttribute("smartComment") SmartCommentBean param, Model model
			, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception{
		
		String userId = (String)session.getAttribute("userId");
		String userNicNm = (String)session.getAttribute("userNicNm");
			
		param.setUserId(userId);
		param.setUserNicNm(userNicNm);
		
		int isSuccess = smartBoardService.smartCommentWriteAction(param);
		boolean result = false;
		if(isSuccess != 0){
			result = true;
		}
		
		JSONObject json=new JSONObject();
		json.put("result", result);
		
	 	response.setContentType("application/json; charset=utf-8");
	 	PrintWriter out=response.getWriter();
	 	out.print(json.toString());
	}
	
	//占쌘몌옙트 占쌜쇽옙
	@RequestMapping(value = "/board/smartBoard/smartCommentDeleteAction.do", method = RequestMethod.POST)
	public void smartCommentDeleteAction(@ModelAttribute("smartComment") SmartCommentBean param, Model model
			, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception{
		
		int isSuccess = smartBoardService.smartCommentDeleteAction(param);
		boolean result = false;
		if(isSuccess != 0){
			result = true;
		}
		
		JSONObject json=new JSONObject();
		json.put("result", result);
		
	 	response.setContentType("application/json; charset=utf-8");
	 	PrintWriter out=response.getWriter();
	 	out.print(json.toString());
	}
	
	//占쌘몌옙트 占쌜쇽옙
	@RequestMapping(value = "/board/smartBoard/smartCommentModifyAction.do", method = RequestMethod.POST)
	public void smartCommentModifyAction(@ModelAttribute("smartComment") SmartCommentBean param, Model model
			, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception{
			
		int isSuccess = smartBoardService.smartCommentModifyAction(param);
		boolean result = false;
		if(isSuccess != 0){
			result = true;
		}
			
		JSONObject json=new JSONObject();
		json.put("result", result);
			
	 	response.setContentType("application/json; charset=utf-8");
	 	PrintWriter out=response.getWriter();
	 	out.print(json.toString());
	}
	//占쏙옙占쏙옙 占쏙옙占싸듸옙	
	
	//占쏙옙占싹억옙占싸듸옙 占쏙옙占쏙옙占쏙옙(占쏙옙占쏙옙)
	@RequestMapping(value = "/board/smartBoard/smartImgAction.do", method = RequestMethod.POST)
	public String file_uploader(HttpServletRequest request, HttpServletResponse response, ImageEditor editor){ 
		
		String return1=request.getParameter("callback"); 
		String return2="?callback_func=" + request.getParameter("callback_func"); String return3=""; String name = "";
		try { 
			if(editor.getFiledata() != null && editor.getFiledata().getOriginalFilename() != null && !editor.getFiledata().getOriginalFilename().equals("")) {
				// 占쏙옙占쏙옙 占쏙옙占� 占쌘드를 占쏙옙占쏙옙 占싹댐옙占쌘드를 占싱울옙 
				name = editor.getFiledata().getOriginalFilename().substring(editor.getFiledata().getOriginalFilename().lastIndexOf(File.separator)+1); 
				String filename_ext = name.substring(name.lastIndexOf(".")+1); 
				filename_ext = filename_ext.toLowerCase(); 
				String[] allow_file = {"jpg","png","bmp","gif"}; 
				int cnt = 0; for(int i=0; i<allow_file.length; i++) {
					if(filename_ext.equals(allow_file[i])){ 
						cnt++; 
					} 
					} if(cnt == 0) {
						return3 = "&errstr="+name; 
					} else { 
						//占쏙옙占쏙옙 占쏙옙占�
						String dftFilePath = request.getSession().getServletContext().getRealPath("/"); 
						//占쏙옙占쏙옙 占썩본占쏙옙占� _ 占쏢세곤옙占� 
						String filePath = "C:/lottery/file/img/";
						File file = new File(filePath); 
						if(!file.exists()) { 
							file.mkdirs(); 
						} 
						String realFileNm = ""; 
						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss"); 
						String today= formatter.format(new java.util.Date()); 
						realFileNm = today+UUID.randomUUID().toString() + name.substring(name.lastIndexOf(".")); 
						String rlFileNm = filePath + realFileNm; 
						///////////////// 占쏙옙占쏙옙占쏙옙 占쏙옙占싹억옙占쏙옙 ///////////////// 
						editor.getFiledata().transferTo(new File(rlFileNm)); 
						///////////////// 占쏙옙占쏙옙占쏙옙 占쏙옙占싹억옙占쏙옙 ///////////////// 
						return3 += "&bNewLine=true"; return3 += "&sFileName="+ name; return3 += "&sFileURL=http://test.qman.co.kr/file/img/"+realFileNm; 
						} 
					}else { 
						return3 += "&errstr=error"; 
					} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
		return "redirect:"+return1+return2+return3; 
	}
	//占쏙옙占싹억옙占싸듸옙 html5	
	@RequestMapping(value = "/board/smartBoard/smartImgHtml5Action.do", method = RequestMethod.POST)
	public void file_uploader_html5(HttpServletRequest request, HttpServletResponse response){
		try { 
			//占쏙옙占쏙옙占쏙옙占쏙옙 
			String sFileInfo = ""; 
			//占쏙옙占싹몌옙占쏙옙 占쌨는댐옙 - 占싹뱄옙 占쏙옙占쏙옙占쏙옙占싹몌옙 
			String filename = request.getHeader("file-name"); 
			//占쏙옙占쏙옙 확占쏙옙占쏙옙 
			String filename_ext = filename.substring(filename.lastIndexOf(".")+1); 
			//확占쏙옙占쌘몌옙占쌀뱄옙占쌘뤄옙 占쏙옙占쏙옙 
			filename_ext = filename_ext.toLowerCase(); 
			//占싱뱄옙占쏙옙 占쏙옙占쏙옙 占썼열占쏙옙占쏙옙 
			String[] allow_file = {"jpg","png","bmp","gif"}; 
			//占쏙옙占쏙옙占썽서 확占쏙옙占쌘곤옙 占싱뱄옙占쏙옙占쏙옙占쏙옙 
			int cnt = 0; for(int i=0; i<allow_file.length; i++) { 
				if(filename_ext.equals(allow_file[i])){ 
					cnt++; 
				} 
			} 
			//占싱뱄옙占쏙옙占쏙옙 占싣댐옙 
			if(cnt == 0) { 
				PrintWriter print = response.getWriter(); 
				print.print("NOTALLOW_"+filename); 
				print.flush(); 
				print.close(); 
			} else { 
				//占싱뱄옙占쏙옙占싱므뤄옙 占신깍옙 占쏙옙占싹뤄옙 占쏙옙占썰리 占쏙옙占쏙옙 占쏙옙 占쏙옙占싸듸옙	
				//占쏙옙占쏙옙 占썩본占쏙옙占� _ 占쏢세곤옙占� 
				String dftFilePath = request.getSession().getServletContext().getRealPath("/"); 

				//占쏙옙占쏙옙 占썩본占쏙옙占� _ 占쏢세곤옙占� 
				String filePath = "C:/lottery/file/img/";
				File file = new File(filePath); 
				if(!file.exists()) { 
					file.mkdirs(); 
				} String realFileNm = ""; 
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss"); 
				String today= formatter.format(new java.util.Date()); 
				realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf(".")); 
				String rlFileNm = filePath + realFileNm; 
				///////////////// 占쏙옙占쏙옙占쏙옙 占쏙옙占싹억옙占쏙옙 ///////////////// 
				InputStream is = request.getInputStream(); 
				OutputStream os=new FileOutputStream(rlFileNm); 
				int numRead; 
				byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while((numRead = is.read(b,0,b.length)) != -1){ 
					os.write(b,0,numRead); 
				} 
				if(is != null) { 
					is.close(); 
				} os.flush(); 
				os.close(); 
				///////////////// 占쏙옙占쏙옙占쏙옙 占쏙옙占싹억옙占쏙옙 ///////////////// 
				// 占쏙옙占쏙옙 占쏙옙占� 
				sFileInfo += "&bNewLine=true"; 
				// img 占승깍옙占쏙옙 title 占쌈쇽옙占쏙옙 占쏙옙占쏙옙占쏙옙占싹몌옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙殮占� 占쏙옙占쏙옙 
				sFileInfo += "&sFileName="+ filename; 
				sFileInfo += "&sFileURL="+"http://test.qman.co.kr/file/img/"+realFileNm; 
				PrintWriter print = response.getWriter(); 
				print.print(sFileInfo); 
				print.flush(); 
				print.close(); 
			}	
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}

	
}
