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

	//ȸ������ ���̵� üũ
	public int checkId(ParamBean param){
		return sqlSession.selectOne("checkId",param);
	}
	
	//ȸ�� ���� �׼�
	public int joinUserAction(UserBean param){
		return sqlSession.insert("joinUserAction", param);
	}
	
	//�α���
	public UserBean loginAction(UserBean param){
		return sqlSession.selectOne("loginAction", param);
	}
	
	//�α��� ���� Ƚ�� ����
	public int UpdateFailCnt(UserBean param){
		return sqlSession.update("UpdateFailCnt", param);
	}
	
	
	
}
