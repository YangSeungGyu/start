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
	
	//게시판 최대 수
	public int getMaxCnt(SimpleBoardBean param){
		return simpleBoardDao.getMaxCnt(param);
	}
	
	//기본 게시판 리스트
	public List<Object> list(SimpleBoardBean param){
			
		return simpleBoardDao.list(param);
	}
	
		
		
	//기본 게시판 글 작성
	public int writeAction(SimpleBoardBean param){
		
		return simpleBoardDao.writeAction(param);
	}
	
	
	
	//게시물 조회수 증가
	public int addCnt(SimpleBoardBean param){
		return simpleBoardDao.addCnt(param);
	}
	
	
	//기본 게시판 상세
	public SimpleBoardBean read(SimpleBoardBean param){
		
		return simpleBoardDao.read(param);
	}
	
	//게시물 삭제
	public int deleteAction(SimpleBoardBean param){
		return simpleBoardDao.deleteAction(param);
	}
	
	//기본 게시판 글 수정
	public int modifyAction(SimpleBoardBean param){
			
		return simpleBoardDao.modifyAction(param);
	}	
	
	
	
	
	
	
	
	
}
