package com.start.www.common.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.start.www.common.bean.UserBean;
import com.start.www.common.dao.CommonDao;

@Service
public class CommonService {
	
	private CommonDao commonDao;

	@Inject
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	


	//��й�ȣ ���� ������Ʈ
	public List<Object> pwQCompList(){
		return commonDao.pwQCompList();
	}
	// ���� ������Ʈ
	public List<Object> jobCompList(){
		return commonDao.jobCompList();
	}
	// ���԰�� ������Ʈ
	public List<Object> joinPathCompList(){
		return commonDao.joinPathCompList();
	}
	// �������� ���� ������Ʈ
	public List<Object> showInfoCompList(){
		return commonDao.showInfoCompList();
	}
	
	//��� ����Ʈ
	public HashMap<String,String> joinContract(HttpServletRequest request)  throws Exception{
		HashMap<String,String> contractTxtMap = new HashMap<String,String>(); 
		
		
        StringBuffer contractTxtBuffer=new StringBuffer();
        String path=request.getSession().getServletContext().getRealPath("/");
        String contractDirectory = null;
        String contract01Directory="/WEB-INF/txt/joinContract01.txt";
        String contract02Directory="/WEB-INF/txt/joinContract02.txt";
        
        
        for(int i=0;i<2;i++){
        	String contractTxt = null;
	        
	        switch (i){
				case 0: contractDirectory = contract01Directory;
				break;
				case 1: contractDirectory = contract02Directory;
				break;
			}
	        
	        String filePath= path+contractDirectory;
	        
	    	InputStreamReader inputSR= new InputStreamReader(new FileInputStream(filePath));
	    	BufferedReader br=new BufferedReader(inputSR);
	    	String line=null;
	    	while((line=br.readLine())!=null){
	    		contractTxtBuffer.append(line+"<br>");
	    	}
	    	
	    	int startIndex= contractDirectory.lastIndexOf("/")+1;
	    	int lastIndex= contractDirectory.lastIndexOf(".");
	    	String fileName=contractDirectory.substring(startIndex, lastIndex);
	    	
	    	contractTxt=contractTxtBuffer.toString();	    	
	    	contractTxtBuffer.delete(0,contractTxtBuffer.capacity());
	        
	    	
	    	switch (i){
				case 0: contractTxtMap.put("contractTxt01", contractTxt);
				break;
				case 1: contractTxtMap.put("contractTxt02", contractTxt);
				break;
	    	}
        }
		
		return contractTxtMap;
	}
	
	
	public boolean checkLogin(HttpSession session){
		boolean isLogin = false;
		String userId = (String)session.getAttribute("userId");
		
		if(userId != null){
			isLogin = true;
		}
		return isLogin;
	}
	
	//���� �α� ���
	public int logAction(String userId,String accessType,String accessCd,HttpServletRequest request){
		/*accessType
		 * L : �α���
		 * O : �α׾ƿ�
		 * M : �޴�
		 * I : �Է�
		 * U : ������Ʈ
		 * D : ����
		 * */
		String accessIp = request.getRemoteAddr();
		
		String accessBrwsr;
		String header = request.getHeader("User-Agent");
		if(header.indexOf("MSIE")>-1){
			accessBrwsr ="IE";
		}else if(header.indexOf("Chrome")>-1){ 
			accessBrwsr ="Chrome";
		}else if(header.indexOf("Opeara")>-1){
			accessBrwsr ="Opera";
		}else if(header.indexOf("Firefox")>-1){
			accessBrwsr ="Firefox";
		}else if(header.indexOf("Safari")>-1){
			accessBrwsr ="Safari";	
		}else if(header.indexOf("rv:")>-1){
			accessBrwsr ="IE";
		}else{
			accessBrwsr ="IE";
		}
		
		
		UserBean param = new UserBean();
		param.setUserId(userId);
		param.setAccessType(accessType);
		param.setAccessCd(accessCd);
		param.setAccessIp(accessIp);
		param.setAccessBrwsr(accessBrwsr);
		return commonDao.registerUserLog(param);
	}

	
}
