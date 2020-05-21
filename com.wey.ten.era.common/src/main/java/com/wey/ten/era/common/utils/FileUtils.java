package com.wey.ten.era.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * 文件工具类
 * @author lenovo
 *
 */
public class FileUtils {

	private final static String defaultPath = "/opt/project/data/";
	/**
	 * 文件上传
	 * @param file
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static ResultObject uploadFile(byte[] file,String fileName) throws Exception { 
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		String filePath = getFileUploadPath();
		FileOutputStream out = null;
		int random=(int)((Math.random()*9+1)*1000);
		try {
			File targetFile = new File(defaultPath+filePath); 
			if(!targetFile.exists()){ 
				targetFile.mkdirs(); 
			} 
			out = new FileOutputStream(defaultPath+filePath+"/"+random+"_"+fileName);
			out.write(file);
			out.flush();
			out.close();
			resultObject.setData(filePath+"/"+random+"_"+fileName);
		} catch (Exception e) {
			resultObject=new ResultObject(ErrorCode.FILE_UPLOAD_FAIL);
		}finally{
			out.close();
		}
		return resultObject;
	}
	
	private static String getFileUploadPath(){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String defaultPath = year+"/"+month+"/"+day;
		return defaultPath;
	}
}
