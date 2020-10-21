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

	//�Խ��� ī�װ�
	public List<Object> smartCategoryList(){
		return sqlSession.selectList("smartCategoryList");
	}
	
	//�Խ��� �ִ� ��
	public int getMaxCnt(SmartBoardBean param){
		return sqlSession.selectOne("smartBoardMaxCnt", param);
	}
	
	//�⺻ �Խ��� ����Ʈ
	public List<Object> smartList(SmartBoardBean param){
		return sqlSession.selectList("smartBoardList", param);
	}
	
	
	//�⺻ �Խ��� ������
	public String smartWriteBoardNo(SmartBoardBean param){
		return sqlSession.selectOne("smartWriteBoardNo");
	}
	
	//�⺻ �Խ��� �ۼ�
	public int smartWriteAction(SmartBoardBean param){
			
		return sqlSession.insert("smartBoardWrite", param);
	}
	
	public void inserFiles(SmartFileBean param){
		sqlSession.insert("smartFileWrite", param);
	}
	
	
	//�Խù� ��ȸ�� ����
	public int addCnt(SmartBoardBean param){
		return  sqlSession.update("smartBoardAddCnt", param);
	}
		
	//�⺻ �Խ��� ��
	public SmartBoardBean smartRead(SmartBoardBean param){
		return sqlSession.selectOne("smartBoardRead", param);
	}
	
	//����Ʈ �Խ��� ���� ��
	public List<SmartBoardBean> smartFileList(SmartBoardBean param){
		return sqlSession.selectList("smartFileList", param);			
	}
	
	//����Ʈ �Խ��� �ڸ�Ʈ ��
	public List<SmartCommentBean> smartCommentList(SmartBoardBean param){
		return sqlSession.selectList("smartCommentList", param);
	}
	
	//------�Խñ� ���� start ------	
	//�Խù� ���� ���� ����Ʈ
	public List<SmartFileBean> smartDeleteFileList(SmartBoardBean param){
		return sqlSession.selectList("smartDeleteFileList", param);
	}
	
	//�ڸ�Ʈ ����
	public int smartIncludeCommentsDelete(SmartBoardBean param){
		return sqlSession.delete("smartIncludeCommentsDelete", param);
	}
	
	//�Խù� ����
	public int smartDeleteAction(SmartBoardBean param){
		return sqlSession.delete("smartBoardDelete", param);
	}
	
	//����ִ� �Խù� ����
	public int smartIncludeReDeleteAction(SmartBoardBean param){
		return sqlSession.update("smartIncludeReDelete", param);
	}
	
	//��ۻ��� �� ������ ������ Ȯ��
	public int smartTopDel(SmartBoardBean param){
		return sqlSession.selectOne("smartTopDel", param);
	}
	
	//�����ۿ� ���ѵ� ��� ����
	public int smartReplyCnt(SmartBoardBean param){
		return sqlSession.selectOne("smartReplyCnt", param);
	}
	//------�Խñ� ���� end ------
	
	//�⺻ �Խ��� �� ����
	public int smartModifyAction(SmartBoardBean param){
		return sqlSession.update("smartBoardModify", param);
	}
	//�⺻ �Խ��� ��� ī�װ� ����
	public int smartIncludeReModifyAction(SmartBoardBean param){
		return sqlSession.update("smartIncludeReModify", param);
	}
	
	//���� ���
	public String getFilePath(String idx){
		return sqlSession.selectOne("smartFilePath", idx);
	}

	//���� ����
	public int deleteFile(String idx){
		return sqlSession.delete("smartFileDelete", idx);
	}
	
	
	
	//�Խù� ��õ Ȯ��
	public int isRcmnd(SmartBoardBean param){
		return sqlSession.selectOne("smartIsRcmnd", param);
	}
	
	
	//�Խù� ��õ ����
	public int smartRcmndCnt(SmartBoardBean param){
		return sqlSession.insert("smartRcmndCnt", param);
	}
			
	//�Խ��� �̸�
	public String getBoardNm(SmartBoardBean param){
			return sqlSession.selectOne("smartBoardNm", param);
	}
	
	//�ڸ�Ʈ �ۼ�
	public int smartCommentWriteAction(SmartCommentBean param){
		return sqlSession.insert("smartCommentWrite", param);
	}
	
	//�ڸ�Ʈ ����
	public int smartCommentDeleteAction(SmartCommentBean param){
		return sqlSession.delete("smartCommentDelete", param);
	}

	//�ڸ�Ʈ ����
	public int smartCommentModifyAction(SmartCommentBean param){
		return sqlSession.update("smartCommentModify", param);
	}	
		
}
