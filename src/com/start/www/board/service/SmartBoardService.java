package com.start.www.board.service;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.start.www.board.bean.SimpleBoardBean;
import com.start.www.board.bean.SmartBoardBean;
import com.start.www.board.bean.SmartCommentBean;
import com.start.www.board.bean.SmartFileBean;
import com.start.www.board.dao.SmartBoardDao;
import com.start.www.common.dao.CommonDao;
import com.start.www.util.DeleteFile;

@Service
public class SmartBoardService {
	
	private SmartBoardDao smartBoardDao;
	private CommonDao commonDao;

	@Inject
	public void setSmartBoardDao(SmartBoardDao smartBoardDao) {
		this.smartBoardDao = smartBoardDao;
	}
	@Inject
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	//�������������� 移닷����怨ㅼ��
	public List<Object> smartCategoryList(){
		return smartBoardDao.smartCategoryList();
	}
	
	//�������������� �������������� �������� ������
		public int getMaxCnt(SmartBoardBean param){
			return smartBoardDao.getMaxCnt(param);
		}
		
	//�������������� �������������� ��������������
	public List<Object> smartList(SmartBoardBean param){
		return smartBoardDao.smartList(param);
	}
	
	//�������������� �������������� ������ �����쎌��
	public int smartWriteAction(SmartBoardBean param,SmartFileBean fileBean){
		String boardNo = smartBoardDao.smartWriteBoardNo(param);
		param.setNo(boardNo);
		
		//������諭��� ������������������ 
		
		 String uploadPath = "C:/lottery/file";
		 
		 int fileCount= fileBean.getSmartFileBeanList().size();
         for(int i=0 ; i<fileCount ; i++){
        	 SmartFileBean fileParam=new SmartFileBean();
         	MultipartFile file=fileBean.getSmartFileBeanList().get(i).getFiles();
         	if(file.isEmpty()){
         		break;
         	}
         	String fileName=file.getOriginalFilename();
         	//������������ ���������밸��� ������������
         	String uniqueId = UUID.randomUUID().toString().replaceAll("-", "");
         	String fileId=uniqueId+"_"+System.currentTimeMillis();
         	fileParam.setBoardNo(boardNo);
         	fileParam.setFileId(fileId);
         	fileParam.setFileNm(fileName);
		         	
         	try{
         		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(uploadPath+"/"+fileId));
         		smartBoardDao.inserFiles(fileParam);
         	}catch(IOException ex){
         	}catch(IllegalStateException ex){
         	}
         }
		
		return smartBoardDao.smartWriteAction(param);
	}
		
	//������諭��� �������������� ������������
	public int addCnt(SmartBoardBean param){
		return smartBoardDao.addCnt(param);
	}
	
	//�������������� �������������� ������
	public SmartBoardBean smartRead(SmartBoardBean param){
			
		return smartBoardDao.smartRead(param);
	}
	
	//�������������� �������������� ������������ ������
	public List<SmartBoardBean> smartFileList(SmartBoardBean param){
				
		return smartBoardDao.smartFileList(param);
	}
	//�������������� �������������� ����紐����� ������
	public List<SmartCommentBean> smartCommentList(SmartBoardBean param){
				
		return smartBoardDao.smartCommentList(param);
	}
	
	//������諭��� ������������
	public int smartDeleteAction(SmartBoardBean param){
		//������������ ������������
		 //��������������������������
		List<SmartFileBean> smartFileDeleteList = smartBoardDao.smartDeleteFileList(param);
			 
		 int fileDeleteCount = 0;
		 if(smartFileDeleteList != null){
			 fileDeleteCount = smartFileDeleteList.size();
		 }
		 for(int i=0;i<fileDeleteCount;i++){
			 String idx= smartFileDeleteList.get(i).getIdx();
			 String path= "C:/lottery/file/"+smartBoardDao.getFilePath(idx);
			new DeleteFile().deleteFile(path);
			 smartBoardDao.deleteFile(idx);
		 }
		 
		 int isSuccess = 0;
		 
		 //����紐����� ������������
		 int delCnt = smartBoardDao.smartIncludeCommentsDelete(param);
		 System.out.println("������������1-����紐����� ������������: ����������������紐����� ������������ = "+delCnt);	
		 
		 //������ ������������
		 if(param.getReply().equals("N")){
			 //���밸�源��� ������������
			 int replyCnt = smartBoardDao.smartReplyCnt(param);
			 //System.out.println("������������2-���밸�源��� ������������: �����������������몄�� �����������몄�� ��������占� ������������ = "+replyCnt);
			 
			 if(replyCnt==0){
				 System.out.println("������������3-���밸�源��� ������������-: ��������占� ������������ ���밸�源��� ������������");
				 //��������玉���占� ��������占� ������������
				  isSuccess = smartBoardDao.smartDeleteAction(param);
			 }else{
				 //��������玉���占� ��������占� ������������
				 // System.out.println("������������3-���밸�源��� ������������-: ��������占� �������� ���밸��� ������諭��� ������������");
				 param.setTitle("(������������������ ������諭��� ����������.)");
				 param.setContents("");
				 isSuccess = smartBoardDao.smartIncludeReDeleteAction(param);
			 }
		 }else{
			 //��������占� ������������
			//��������占� ������諭��� ������������
			 isSuccess = smartBoardDao.smartDeleteAction(param);
			 //������������������ ������������ ������������ ��������
			 int topDel =  smartBoardDao.smartTopDel(param);
			 //System.out.println("������������2-��������占� ������������-: ��������占� ����源����������� ������������ / ������������������ ������������������ ������������ : " + topDel);
			 boolean isTopDel = topDel > 0 ? true : false;
			 if(isTopDel){
				 //������������ ������諭��� ������������
				 //System.out.println("������������3-��������占� ������������-: ������������������ ������������ ������ ������������");
				 param.setNo(param.getTopNo());
				 smartBoardDao.smartDeleteAction(param);
			 }
		 }
		return isSuccess;
	}
		
	public int smartModifyAction(SmartBoardBean param,SmartFileBean fileBean){
		 String boardNo = param.getNo();
		 
		 List<SmartFileBean> smartFileDeleteList = fileBean.getSmartFileDeleteList();
		 
		 int fileDeleteCount = 0;
		 if(smartFileDeleteList != null){
			 fileDeleteCount = smartFileDeleteList.size();
		 }
		 for(int i=0;i<fileDeleteCount;i++){
			 String idx= smartFileDeleteList.get(i).getDeleteFile();
			 String path= "C:/lottery/file/"+smartBoardDao.getFilePath(idx);
			new DeleteFile().deleteFile(path);
			 smartBoardDao.deleteFile(idx);
		 }
		 
		 
		 List<SmartFileBean> smartFileList = fileBean.getSmartFileBeanList();
		 //System.out.println("����琉��� ���������밴낀��������!!!"+smartFileList.size());
		 String uploadPath = "C:/lottery/file/";		 
		 int fileCount=smartFileList.size();
         for(int i=0 ; i<fileCount ; i++){
        	 SmartFileBean fileParam=new SmartFileBean();
         	MultipartFile file=smartFileList.get(i).getFiles();
         	if(file.isEmpty()){
         		break;
         	}
         	String fileName=file.getOriginalFilename();
         	String uniqueId = UUID.randomUUID().toString().replaceAll("-", "");
         	String fileId=uniqueId+"_"+System.currentTimeMillis();
         	fileParam.setBoardNo(boardNo);
         	fileParam.setFileId(fileId);
         	fileParam.setFileNm(fileName);
		         	
         	try{
         		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(uploadPath+"/"+fileId));
         		smartBoardDao.inserFiles(fileParam);
         	}catch(IOException ex){
         		 System.out.println("IOException");
         	}catch(IllegalStateException ex){
         		System.out.println("IllegalStateException");
         	}
         }		
		
         int isSuccess = smartBoardDao.smartModifyAction(param);
         //��������������占� ������������ ������ 移닷����怨ㅼ�� ���깅�몄�� ������ ������������ ���깅�몄��������
         if(param.getChangeCacategory().equals("Y")){
        	 smartBoardDao.smartIncludeReModifyAction(param);
         }
		
		return isSuccess;
	}	
	
	
	//������諭��� ������泥� ������������
	public Map<String,Object> smartRcmndCnt(SmartBoardBean param){
		HashMap<String,Object> result = new HashMap<String,Object>(); 
		int isRcmnd = smartBoardDao.isRcmnd(param);
		
		if(isRcmnd == 0){
			int smartRcmndCnt = smartBoardDao.smartRcmndCnt(param);
			result.put("msg", "�������� ������諭��������� ������泥����뱀�몄�����������밸����.");
		}else{
			result.put("msg", "���깅��� ������泥����뱀���� ������諭��� ����������.");
		}
		return result;
	}
	
	//�������������� 移닷����怨ㅼ�� ���깅���
	public String getBoardNm(SmartBoardBean param){
		return smartBoardDao.getBoardNm(param);
	}
	
	//����紐����� �����쎌��
	public int smartCommentWriteAction(SmartCommentBean param){
		return smartBoardDao.smartCommentWriteAction(param);
	}
	
	//����紐����� ������������
	public int smartCommentDeleteAction(SmartCommentBean param){
		return smartBoardDao.smartCommentDeleteAction(param);
	}
	
	//����紐����� ������������
	public int smartCommentModifyAction(SmartCommentBean param){
		return smartBoardDao.smartCommentModifyAction(param);
	}
	
}
