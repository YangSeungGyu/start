package com.start.www.board.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.www.board.bean.SimpleBoardBean;

@Repository("simpleBoardSql")
public class SimpleBoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	//�Խ��� �ִ� ��
	public int getMaxCnt(SimpleBoardBean param){
		return sqlSession.selectOne("simpleBoardMaxCnt", param);
	}
	
	//�⺻ �Խ��� ����Ʈ
	public List<Object> list(SimpleBoardBean param){
		return sqlSession.selectList("simpleBoardList", param);
	}
	//�⺻ �Խ��� ����Ʈ
	public int writeAction(SimpleBoardBean param){
		
		return sqlSession.insert("simpleBoardWrite", param);
	}
	
	//�Խù� ��ȸ�� ����
	public int addCnt(SimpleBoardBean param){
		return  sqlSession.update("simpleBoardAddCnt", param);
	}
	
	//�⺻ �Խ��� ��
	public SimpleBoardBean read(SimpleBoardBean param){
		
		return sqlSession.selectOne("simpleBoardRead", param);
	}
	
	//�Խù� ����
	public int deleteAction(SimpleBoardBean param){
		return sqlSession.delete("simpleBoardDelete", param);
	}
	
	//�⺻ �Խ��� �� ����
	public int modifyAction(SimpleBoardBean param){
		
		return sqlSession.update("simpleBoardModify", param);
	}	
}
