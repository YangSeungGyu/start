package com.start.www.common.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.www.common.bean.UserBean;

@Repository("commonSql")
public class CommonDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

		
	public List<Object> pwQCompList(){
			List<Object> list = sqlSession.selectList("pwQCompList");
			return list;
		}
	
	public List<Object> jobCompList(){
		List<Object> list = sqlSession.selectList("jobCompList");
		return list;
	}
	
	public List<Object> joinPathCompList(){
		List<Object> list = sqlSession.selectList("joinPathCompList");
		return list;
	}
	
	public List<Object> showInfoCompList(){
		List<Object> list = sqlSession.selectList("showInfoCompList");
		return list;
	}
	
	//로그 기록
	public int registerUserLog(UserBean param){
		return sqlSession.insert("registerUserLog", param);
	}
}
