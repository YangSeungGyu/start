package com.start.www.board.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.start.www.board.bean.SimpleBoardBean;
import com.start.www.board.dao.SimpleBoardDao;
import com.start.www.common.dao.CommonDao;

@Service
public class SimpleBoardService {
	
	private SimpleBoardDao simpleBoardDao;
	private CommonDao commonDao;

	@Inject
	public void setSimpleBoardDao(SimpleBoardDao simpleBoardDao) {
		this.simpleBoardDao = simpleBoardDao;
	}
	@Inject
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	//�Խ��� �ִ� ��
	public int getMaxCnt(SimpleBoardBean param){
		return simpleBoardDao.getMaxCnt(param);
	}
	
	//�⺻ �Խ��� ����Ʈ
	public List<Object> list(SimpleBoardBean param){
			
		return simpleBoardDao.list(param);
	}
	
		
		
	//�⺻ �Խ��� �� �ۼ�
	public int writeAction(SimpleBoardBean param){
		
		return simpleBoardDao.writeAction(param);
	}
	
	
	
	//�Խù� ��ȸ�� ����
	public int addCnt(SimpleBoardBean param){
		return simpleBoardDao.addCnt(param);
	}
	
	
	//�⺻ �Խ��� ��
	public SimpleBoardBean read(SimpleBoardBean param){
		
		return simpleBoardDao.read(param);
	}
	
	//�Խù� ����
	public int deleteAction(SimpleBoardBean param){
		return simpleBoardDao.deleteAction(param);
	}
	
	//�⺻ �Խ��� �� ����
	public int modifyAction(SimpleBoardBean param){
			
		return simpleBoardDao.modifyAction(param);
	}	
	
	
	
	
	
	
	
	
}
