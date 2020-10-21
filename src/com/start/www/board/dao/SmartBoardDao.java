package com.start.www.board.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.www.board.bean.SimpleBoardBean;
import com.start.www.board.bean.SmartBoardBean;
import com.start.www.board.bean.SmartCommentBean;
import com.start.www.board.bean.SmartFileBean;


@Repository("smartBoardSql")
public class SmartBoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	//게시판 카테고리
	public List<Object> smartCategoryList(){
		return sqlSession.selectList("smartCategoryList");
	}
	
	//게시판 최대 수
	public int getMaxCnt(SmartBoardBean param){
		return sqlSession.selectOne("smartBoardMaxCnt", param);
	}
	
	//기본 게시판 리스트
	public List<Object> smartList(SmartBoardBean param){
		return sqlSession.selectList("smartBoardList", param);
	}
	
	
	//기본 게시판 시퀀스
	public String smartWriteBoardNo(SmartBoardBean param){
		return sqlSession.selectOne("smartWriteBoardNo");
	}
	
	//기본 게시판 작성
	public int smartWriteAction(SmartBoardBean param){
			
		return sqlSession.insert("smartBoardWrite", param);
	}
	
	public void inserFiles(SmartFileBean param){
		sqlSession.insert("smartFileWrite", param);
	}
	
	
	//게시물 조회수 증가
	public int addCnt(SmartBoardBean param){
		return  sqlSession.update("smartBoardAddCnt", param);
	}
		
	//기본 게시판 상세
	public SmartBoardBean smartRead(SmartBoardBean param){
		return sqlSession.selectOne("smartBoardRead", param);
	}
	
	//스마트 게시판 파일 상세
	public List<SmartBoardBean> smartFileList(SmartBoardBean param){
		return sqlSession.selectList("smartFileList", param);			
	}
	
	//스마트 게시판 코멘트 상세
	public List<SmartCommentBean> smartCommentList(SmartBoardBean param){
		return sqlSession.selectList("smartCommentList", param);
	}
	
	//------게시글 삭제 start ------	
	//게시물 삭제 파일 리스트
	public List<SmartFileBean> smartDeleteFileList(SmartBoardBean param){
		return sqlSession.selectList("smartDeleteFileList", param);
	}
	
	//코멘트 삭제
	public int smartIncludeCommentsDelete(SmartBoardBean param){
		return sqlSession.delete("smartIncludeCommentsDelete", param);
	}
	
	//게시물 삭제
	public int smartDeleteAction(SmartBoardBean param){
		return sqlSession.delete("smartBoardDelete", param);
	}
	
	//답글있는 게시물 삭제
	public int smartIncludeReDeleteAction(SmartBoardBean param){
		return sqlSession.update("smartIncludeReDelete", param);
	}
	
	//답글삭제 시 삭제된 상위글 확인
	public int smartTopDel(SmartBoardBean param){
		return sqlSession.selectOne("smartTopDel", param);
	}
	
	//삭제글에 포한된 답글 개수
	public int smartReplyCnt(SmartBoardBean param){
		return sqlSession.selectOne("smartReplyCnt", param);
	}
	//------게시글 삭제 end ------
	
	//기본 게시판 글 수정
	public int smartModifyAction(SmartBoardBean param){
		return sqlSession.update("smartBoardModify", param);
	}
	//기본 게시판 답글 카테고리 수정
	public int smartIncludeReModifyAction(SmartBoardBean param){
		return sqlSession.update("smartIncludeReModify", param);
	}
	
	//파일 경로
	public String getFilePath(String idx){
		return sqlSession.selectOne("smartFilePath", idx);
	}

	//파일 삭제
	public int deleteFile(String idx){
		return sqlSession.delete("smartFileDelete", idx);
	}
	
	
	
	//게시물 추천 확인
	public int isRcmnd(SmartBoardBean param){
		return sqlSession.selectOne("smartIsRcmnd", param);
	}
	
	
	//게시물 추천 증가
	public int smartRcmndCnt(SmartBoardBean param){
		return sqlSession.insert("smartRcmndCnt", param);
	}
			
	//게시판 이름
	public String getBoardNm(SmartBoardBean param){
			return sqlSession.selectOne("smartBoardNm", param);
	}
	
	//코멘트 작성
	public int smartCommentWriteAction(SmartCommentBean param){
		return sqlSession.insert("smartCommentWrite", param);
	}
	
	//코멘트 삭제
	public int smartCommentDeleteAction(SmartCommentBean param){
		return sqlSession.delete("smartCommentDelete", param);
	}

	//코멘트 수정
	public int smartCommentModifyAction(SmartCommentBean param){
		return sqlSession.update("smartCommentModify", param);
	}	
		
}
