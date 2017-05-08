package com.naruto.myfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
/**
 * @ClassName: FileUtils 
 * @Description: 文件操作工具类
 * @author zhaochenxi
 * @date 2016年11月30日 下午4:55:52
 */
public class FileUtils {

	private FileUtils(){
		
	}
	
	public static final String XML_BASE_PATH = "/Users/aa/Downloads/xmlpath/";
	
	/**
	 * @Title: createXMLFile 
	 * @Description: 创建XML文件，并且把文件内容写到文件中。 
	 * @param 
	 * @return boolean 
	 * @throws 
	 * @author zhaochenxi
	 */
	public static boolean createXMLFile(String path,String fileName,String xmlContext){
		File file = new File(path+fileName+".xml");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(xmlContext==null||"".equals(xmlContext)){
					return true;
				}
				OutputStream out = new FileOutputStream(file);
				out.write(xmlContext.getBytes("utf-8"));
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}else{
			throw new RuntimeException("文件已经存在了");
		}
		return false;
	}
	
	public static void main(String[] args) {
		createXMLFile(XML_BASE_PATH,"test","test");
	}
}
