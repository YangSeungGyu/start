package com.start.www.admin.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.www.admin.bean.HomeMainInfoBean;
import com.start.www.admin.bean.LogBean;
import com.start.www.board.bean.SimpleBoardBean;

@Repository("adminHomePageSql")
public class AdminHomeDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// ������ ����	
	public HomeMainInfoBean mainInfo(){
		return sqlSession.selectOne("mainInfo");
	}

	//�α� �ִ� ��
	public int getMaxCnt(){
		return sqlSession.selectOne("logMaxCnt");
	}
	
	//�α� ������
	public List<Object> logInfo(LogBean param){
		return sqlSession.selectList("logInfoList",param);
	}
}
