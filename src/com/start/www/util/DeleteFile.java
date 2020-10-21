package com.start.www.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** log4j */

public class DeleteFile {
	private final Log logger = LogFactory.getLog(getClass());
	 public void deleteFile(String filePath)
	    {	
	    	try{
	    		File file = new File(filePath);
	    		if(file.delete()){
	    			logger.info(file.getName() + " is deleted!");
	    		}else{
	    			logger.info("Delete operation is failed.");
	    		}
	 
	    	}catch(Exception e){
	 
	    		e.printStackTrace();
	 
	    	}
	 
	    }
}
