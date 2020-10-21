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

	// 관리자 메인	
	public HomeMainInfoBean mainInfo(){
		return sqlSession.selectOne("mainInfo");
	}

	//로그 최대 수
	public int getMaxCnt(){
		return sqlSession.selectOne("logMaxCnt");
	}
	
	//로그 페이지
	public List<Object> logInfo(LogBean param){
		return sqlSession.selectList("logInfoList",param);
	}
}
