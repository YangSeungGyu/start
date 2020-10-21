package com.start.www.admin.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.start.www.admin.bean.HomeMainInfoBean;
import com.start.www.admin.bean.LogBean;
import com.start.www.admin.dao.AdminHomeDao;
import com.start.www.board.bean.SimpleBoardBean;
import com.start.www.board.dao.SimpleBoardDao;
import com.start.www.common.dao.CommonDao;

@Service
public class AdminHomeService {
	
	private CommonDao commonDao;
	private AdminHomeDao adminHomeDao;

	@Inject
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	@Inject
	public void setAdminHomeDao(AdminHomeDao adminHomeDao) {
		this.adminHomeDao = adminHomeDao;
	}
	
	
	//������ ����
	public HomeMainInfoBean mainInfo(){
		return adminHomeDao.mainInfo();
	}
	
	//�α� �ִ� ��
	public int getMaxCnt(){
		return adminHomeDao.getMaxCnt();
	}
	
	//�α� ������
	public List<Object> logInfo(LogBean param){
		return adminHomeDao.logInfo(param);
	}
	

}
