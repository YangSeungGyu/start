package com.start.www.board.bean;

public class SimpleBoardBean {
	private String no;
	private String title;
	private String contents;
	private String userId;
	private String userNicNm;
	private String viewCount;
	private String stDt;
	private String upDt;
	private String useYn;
	private String isNew;
	private String upUserId; 
	
	
	private int crntPage;
	private int firstRow;
	private int lastRow;
	private int firstPage;
	private int lastPage;
	private boolean isPrewPage;
	private boolean isNextPage;
	private int maxPageCnt;
	
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
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	public String getStDt() {
		return stDt;
	}
	public void setStDt(String stDt) {
		this.stDt = stDt;
	}
	public String getUpDt() {
		return upDt;
	}
	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getUpUserId() {
		return upUserId;
	}
	public void setUpUserId(String upUserId) {
		this.upUserId = upUserId;
	}
	
	
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
	
	
	
	
	
}
