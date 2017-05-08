package com.naruto.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class FileUtils {

	public static final String JMX_BASE_PATH = "/Users/aa/Downloads/xmlpath/";
	
	public static File createPngFile(String path,String fileName,String context){
		
		File file = new File(path+fileName+".png");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(context==null){
					return null;
				}
				
				OutputStream out = new FileOutputStream(file);
				out.write(context.getBytes());				
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
		return file;
	}
	
	public static void main(String[] args) {
	//	createPngFile(JMX_BASE_PATH,"test","");
		  String s="${dhfjdhsakfhdksa}";
		  s=s.substring(2,s.length()-1);
		  System.out.println(s);
	}

}
