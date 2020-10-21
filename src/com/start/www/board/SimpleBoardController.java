package com.start.www.board;

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

import com.start.www.board.bean.SimpleBoardBean;
import com.start.www.board.service.SimpleBoardService;
import com.start.www.common.bean.ParamBean;
import com.start.www.common.bean.UserBean;
import com.start.www.common.service.CommonService;
import com.start.www.common.service.UserService;

import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */

@Controller
public class SimpleBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleBoardController.class);
	
	private SimpleBoardService simpleBoardService;
	private CommonService commonService;
	

	@Inject
	public void setSimpleBoardService(SimpleBoardService simpleBoardService) {
		this.simpleBoardService = simpleBoardService;
	}
	@Inject
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	

		
		//�⺻ �Խ��� ����Ʈ
		@RequestMapping(value = "/board/simpleBoard/list.do", method = RequestMethod.GET)
		public String list(@ModelAttribute("simpleBoardBean") SimpleBoardBean param,Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
			/*���� �α� ���
			 * */
			commonService.logAction((String)session.getAttribute("userId"),"M","800001",request);
			
			int crntPage = 1; //����������
			if(param.getCrntPage() != 0){
				crntPage = param.getCrntPage(); 
			}
			
			int maxRow = 10; //��� row��
			double maxPage = 5.0; //��� page ����
			int maxRowCnt = simpleBoardService.getMaxCnt(param); //�Խù� �ִ��
			int maxPageCnt = (int)Math.ceil(maxRowCnt/(double)maxRow); //page �ִ� ��
			
			//�˻����� ����
			int firstRow = ((crntPage * maxRow)-maxRow)+1;  
			int lastRow = ((crntPage * maxRow)); 
			
			
			boolean isPrewPage = false;
			boolean isNextPage = false;
			
			
			//������ ��� ����
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

			
			List<Object> simpleBoardList = simpleBoardService.list(param);
			model.addAttribute("pageInfo", param);
			model.addAttribute("result", simpleBoardList);
			return "board/simpleBoard/list";
		}
		
		//�⺻ �Խ��� �ۼ� ������
		@RequestMapping(value = "/board/simpleBoard/write.do", method = RequestMethod.GET)
		public String write(Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
			boolean isLogin = commonService.checkLogin(session);
			
			if(!isLogin){
				model.addAttribute("redirectUrl", "/board/simpleBoard/list.do");
				model.addAttribute("redirectMsg", "�α��� �� ��� �Ͻ� �� �ֽ��ϴ�.");
				return "forward:/common/redirectMsg.do";
			}else{
				return "board/simpleBoard/write";
			}
			
		}
		
		//�⺻ �Խ��� �ۼ�
		@RequestMapping(value = "/board/simpleBoard/writeAction.do", method = RequestMethod.POST)
		public String writeAction(@ModelAttribute("simpleBoardBean") SimpleBoardBean param,Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
			//���� �α� ���
			commonService.logAction((String)session.getAttribute("userId"),"I","800001",request);
			
			
			String userId = (String)session.getAttribute("userId");
			String userNicNm = (String)session.getAttribute("userNicNm");
			
			param.setUserId(userId);
			param.setUserNicNm(userNicNm);
			
			int isSucess = simpleBoardService.writeAction(param);
			
			
			model.addAttribute("redirectUrl", "/board/simpleBoard/list.do");
			model.addAttribute("redirectMsg", "���� ���������� ��� �Ǿ����ϴ�.");
			return "forward:/common/redirectMsg.do";
			
		}
		
		//�⺻ �Խ��� ��
		@RequestMapping(value = "/board/simpleBoard/read.do", method = RequestMethod.GET)
		public String read(@ModelAttribute("simpleBoardBean") SimpleBoardBean param,Locale locale, Model model, HttpServletRequest request) throws Exception{
					
			int isSucess = simpleBoardService.addCnt(param);
					
			SimpleBoardBean simpleBoardRead = simpleBoardService.read(param);
			model.addAttribute("result", simpleBoardRead);
			return "board/simpleBoard/read";
		}
		
		//�⺻ �Խ��� ����
		@RequestMapping(value = "/board/simpleBoard/deleteAction.do", method = RequestMethod.GET)
		public String deleteAction(@ModelAttribute("simpleBoardBean") SimpleBoardBean param,Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
			//���� �α� ���
			commonService.logAction((String)session.getAttribute("userId"),"D","800001",request);		
			
			int isSucess = simpleBoardService.deleteAction(param);
			
			model.addAttribute("redirectUrl", "/board/simpleBoard/list.do");
			model.addAttribute("redirectMsg", "���� ���������� ���� �Ǿ����ϴ�.");
			return "forward:/common/redirectMsg.do";
		}		
		
		//�⺻ �Խ��� ���� ������
		@RequestMapping(value = "/board/simpleBoard/modify.do", method = RequestMethod.POST)
		public String modify(@ModelAttribute("simpleBoardBean") SimpleBoardBean param, Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
			model.addAttribute("result", param);
			return "board/simpleBoard/modify";
		}		
		
		//�⺻ �Խ��� ����
		@RequestMapping(value = "/board/simpleBoard/modifyAction.do", method = RequestMethod.POST)
		public String modifyAction(@ModelAttribute("simpleBoardBean") SimpleBoardBean param,Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception{
			//���� �α� ���
			commonService.logAction((String)session.getAttribute("userId"),"U","800001",request);	
			String userId = (String)session.getAttribute("userId");
			param.setUpUserId(userId);
					
			int isSucess = simpleBoardService.modifyAction(param);
					
			model.addAttribute("redirectUrl", "/board/simpleBoard/list.do");
			model.addAttribute("redirectMsg", "���� ���������� ���� �Ǿ����ϴ�.");
			return "forward:/common/redirectMsg.do";
		}
		
		
}
