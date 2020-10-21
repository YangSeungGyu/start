package com.start.www.board.bean;

import org.springframework.web.multipart.MultipartFile;

public class ImageEditor {
	private MultipartFile Filedata;

	public MultipartFile getFiledata() {
		return Filedata;
	}

	public void setFiledata(MultipartFile filedata) {
		Filedata = filedata;
	}

	
}
