package com.start.www.board.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SmartCommentBean {

	
	private String idx;
	private String boardNo;
	private String contents;
	private String status;
	private String userId;
	private String userNicNm;
	private String inDt;
	private String upDt;

	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNicNm() {
		return userNicNm;
	}
	public void setUserNicNm(String userNicNm) {
		this.userNicNm = userNicNm;
	}
	public String getInDt() {
		return inDt;
	}
	public void setInDt(String inDt) {
		this.inDt = inDt;
	}
	public String getUpDt() {
		return upDt;
	}
	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}
	
	
}
