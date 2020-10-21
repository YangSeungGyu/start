package com.start.www.board.bean;

import org.springframework.web.multipart.MultipartFile;

public class SmartBoardBean {
	  private String no;
	  private String category;
	  private String PageCategory;
	  private String categoryNm;
	  private String title;
	  private String contents;
	  private String publicLv;
	  private String publicLvNm;
	  private String reply;
	  private String userId;
	  private String upUserId;
	  private String userNicNm;
	  private int viewCnt;
	  private int rcmndCnt;
	  private String inDt;
	  private String upDt;
	  private String status;
	  private String isFile;
	  private String isNew;
	  private String fileCnt;
	  private String commentCnt;
	  private String topNo;
	  private String changeCacategory;
	  private String isImg;
	  
	  
	  private int crntPage;
	  private int firstRow;
	  private int lastRow;
	  private int firstPage;
	  private int lastPage;
	  private boolean isPrewPage = false;
	  private boolean isNextPage = false;
	  private int maxPageCnt;
	  private String searchOrder;
	  private String searchPart;
	  private String searchTerm;
	  
	  
	  private String userType;
	  
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPageCategory() {
		return PageCategory;
	}
	public void setPageCategory(String pageCategory) {
		PageCategory = pageCategory;
	}
	public String getCategoryNm() {
		return categoryNm;
	}
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPublicLv() {
		return publicLv;
	}
	public void setPublicLv(String publicLv) {
		this.publicLv = publicLv;
	}

	public String getPublicLvNm() {
		return publicLvNm;
	}
	public void setPublicLvNm(String publicLvNm) {
		this.publicLvNm = publicLvNm;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUpUserId() {
		return upUserId;
	}
	public void setUpUserId(String upUserId) {
		this.upUserId = upUserId;
	}
	public String getUserNicNm() {
		return userNicNm;
	}
	public void setUserNicNm(String userNicNm) {
		this.userNicNm = userNicNm;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getRcmndCnt() {
		return rcmndCnt;
	}
	public void setRcmndCnt(int rcmndCnt) {
		this.rcmndCnt = rcmndCnt;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getIsFile() {
		return isFile;
	}
	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}
	public String getTopNo() {
		return topNo;
	}
	public void setTopNo(String topNo) {
		this.topNo = topNo;
	}
	public String getChangeCacategory() {
		return changeCacategory;
	}
	public void setChangeCacategory(String changeCacategory) {
		this.changeCacategory = changeCacategory;
	}
	public String getIsImg() {
		return isImg;
	}
	public void setIsImg(String isImg) {
		this.isImg = isImg;
	}
	
	
	
	
	//∆‰¿Ã¬° ¡§∫∏
	public int getCrntPage() {
		return crntPage;
	}
	public void setCrntPage(int crntPage) {
		this.crntPage = crntPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public boolean getIsPrewPage() {
		return isPrewPage;
	}
	public void setIsPrewPage(boolean isPrewPage) {
		this.isPrewPage = isPrewPage;
	}
	public boolean getIsNextPage() {
		return isNextPage;
	}
	public void setIsNextPage(boolean isNextPage) {
		this.isNextPage = isNextPage;
	}
	public int getMaxPageCnt() {
		return maxPageCnt;
	}
	public void setMaxPageCnt(int maxPageCnt) {
		this.maxPageCnt = maxPageCnt;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getFileCnt() {
		return fileCnt;
	}
	public void setFileCnt(String fileCnt) {
		this.fileCnt = fileCnt;
	}
	public String getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(String commentCnt) {
		this.commentCnt = commentCnt;
	}
	public String getSearchOrder() {
		return searchOrder;
	}
	public void setSearchOrder(String searchOrder) {
		this.searchOrder = searchOrder;
	}
	public String getSearchPart() {
		return searchPart;
	}
	public void setSearchPart(String searchPart) {
		this.searchPart = searchPart;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	

	
	
	  
	  
}
