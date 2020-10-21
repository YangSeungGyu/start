package com.start.www.board.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SmartFileBean {
	//자기자신을 배열로 다중Bean
	private List<SmartFileBean> smartFileBeanList;
	private List<SmartFileBean> smartFileDeleteList;
	private String idx;
	private String boardNo;
	private String fileId;
	private String fileNm;
	MultipartFile files;
	private String deleteFile;
	
	

	
	
	public List<SmartFileBean> getSmartFileBeanList() {
		return smartFileBeanList;
	}
	public void setSmartFileBeanList(List<SmartFileBean> smartFileBeanList) {
		this.smartFileBeanList = smartFileBeanList;
	}
	
	public List<SmartFileBean> getSmartFileDeleteList() {
		return smartFileDeleteList;
	}
	public void setSmartFileDeleteList(List<SmartFileBean> smartFileDeleteList) {
		this.smartFileDeleteList = smartFileDeleteList;
	}
	
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
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String deleteFile) {
		this.deleteFile = deleteFile;
	}
	public MultipartFile getFiles() {
		return files;
	}
	public void setFiles(MultipartFile files) {
		this.files = files;
	}

	
	
	
	
}
