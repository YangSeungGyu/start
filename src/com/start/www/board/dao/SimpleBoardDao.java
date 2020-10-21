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

	
	//게시판 최대 수
	public int getMaxCnt(SimpleBoardBean param){
		return sqlSession.selectOne("simpleBoardMaxCnt", param);
	}
	
	//기본 게시판 리스트
	public List<Object> list(SimpleBoardBean param){
		return sqlSession.selectList("simpleBoardList", param);
	}
	//기본 게시판 리스트
	public int writeAction(SimpleBoardBean param){
		
		return sqlSession.insert("simpleBoardWrite", param);
	}
	
	//게시물 조회수 증가
	public int addCnt(SimpleBoardBean param){
		return  sqlSession.update("simpleBoardAddCnt", param);
	}
	
	//기본 게시판 상세
	public SimpleBoardBean read(SimpleBoardBean param){
		
		return sqlSession.selectOne("simpleBoardRead", param);
	}
	
	//게시물 삭제
	public int deleteAction(SimpleBoardBean param){
		return sqlSession.delete("simpleBoardDelete", param);
	}
	
	//기본 게시판 글 수정
	public int modifyAction(SimpleBoardBean param){
		
		return sqlSession.update("simpleBoardModify", param);
	}	
}
