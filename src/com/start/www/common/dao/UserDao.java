package com.start.www.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.www.common.bean.ParamBean;
import com.start.www.common.bean.UserBean;

@Repository("userSql")
public class UserDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	//회원가입 아이디 체크
	public int checkId(ParamBean param){
		return sqlSession.selectOne("checkId",param);
	}
	
	//회원 가입 액션
	public int joinUserAction(UserBean param){
		return sqlSession.insert("joinUserAction", param);
	}
	
	//로그인
	public UserBean loginAction(UserBean param){
		return sqlSession.selectOne("loginAction", param);
	}
	
	//로그인 실패 횟수 변경
	public int UpdateFailCnt(UserBean param){
		return sqlSession.update("UpdateFailCnt", param);
	}
	
	
	
}
