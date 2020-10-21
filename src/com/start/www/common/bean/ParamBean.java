package com.start.www.common.bean;

public class ParamBean {
	private String paramIsAdvertis;
	private String paramUserId;
	
	private String redirectUrl;

	private String redirectMsg;
	
	public String getParamUserId() {
		return paramUserId;
	}
	public void setParamUserId(String paramUserId) {
		this.paramUserId = paramUserId;
	}
	public String getParamIsAdvertis() {
		return paramIsAdvertis;
	}
	public void setParamIsAdvertis(String paramIsAdvertis) {
		this.paramIsAdvertis = paramIsAdvertis;
	}
	
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getRedirectMsg() {
		return redirectMsg;
	}
	public void setRedirectMsg(String redirectMsg) {
		this.redirectMsg = redirectMsg;
	}
	
	
}
