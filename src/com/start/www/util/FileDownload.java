package com.start.www.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownload extends AbstractView { 
	public void FileDownload2(){ 
		setContentType("application/download; utf-8"); 
	} //파일 다운로드 
	
	@Override 
	protected void renderMergedOutputModel(Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		File file = (File)paramMap.get("file");
        String fileName=(String)paramMap.get("fileName");
		
		response.setContentType(getContentType()); 
		response.setContentLength((int)file.length()); 
		
		String temp_fileName=fileName; 
		String ori_fileName = getDisposition(temp_fileName, getBrowser(request)); 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + ori_fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		OutputStream out = response.getOutputStream(); 
		FileInputStream fis = null; 
		try { 
			fis = new FileInputStream(file); FileCopyUtils.copy(fis, out); 
		} catch(Exception e){ 
			e.printStackTrace(); 
		}finally{ 
			if(fis != null){ 
				try{ fis.close(); 
			}catch(Exception e){} 
		} 
	} 
		out.flush(); 
	}

	
	//특수 문자 학인
	private String getDisposition(String filename, String browser) throws Exception {
		String encodedFilename = null; if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20"); 
			} else if (browser.equals("Firefox")) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\""; 
			} else if (browser.equals("Opera")) { 
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\""; 
			} else if (browser.equals("Chrome")) { 
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < filename.length(); i++) {
					char c = filename.charAt(i); if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8")); 
			} else { sb.append(c); 
			} 
		} encodedFilename = sb.toString(); 
		} else { 
			throw new RuntimeException("Not supported browser"); 
		} return encodedFilename; 
	}
	
	//브라우저 확인
	private String getBrowser(HttpServletRequest request) { 
		String header = request.getHeader("User-Agent"); 
		if (header.indexOf("MSIE") > -1) { 
			return "MSIE"; 
		} else if (header.indexOf("Chrome") > -1) { 
			return "Chrome"; 
		} else if (header.indexOf("Opera") > -1) { 
			return "Opera"; 
		} else if (header.indexOf("Trident/7.0") > -1){ 
			//IE 11 이상
			//IE 버전 별 체크 >> Trident/6.0(IE 10) , Trident/5.0(IE 9) , Trident/4.0(IE 8)
			return "MSIE"; 
		} return "Firefox"; 
	}
}
