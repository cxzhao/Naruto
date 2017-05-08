package com.naruto.spider;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 抓取链接数据
 * @ClassName: UriCatch 
 * @Description: 请求网页，获取网页数据
 * @author zhaochenxi
 * @date 2016年10月11日 上午11:55:05
 */
public class UrlCatch {

	public String catchUrl(String url){
		String html=null;
		try {
			html = Jsoup.connect(url).execute().body();
		} catch(SocketTimeoutException stex){			
			stex.printStackTrace();		
			System.out.println("连接"+url+"超时");
			return null;
		}catch (IOException e) {
			e.printStackTrace();
		} 
		return html;		
	}
	
	public Element getElementById(String html,String id){
		if(html==null||"".equals(html)){
			return null;
		}
		Document doc = Jsoup.parse(html);
		Element element=doc.getElementById(id);
		return element;
	}
	
	/**
	 * 根据html的class熟悉获取html内容
	 * @Title: getElementByClass 
	 * @param  
	 * @return Element 
	 * @throws 
	 * @author zhaochenxi
	 */
	public Elements getTudouCartoonUpdate(Element element){
		if(element==null){
			return null;
		}
		Elements elements=element.getElementsByTag("li");
		for(int i=0;i<elements.size();i++){
			Element e = elements.get(i);
			if(e!=null){
			}
		}
		return elements;		
	}
	
	
	public static void main(String[] args) {
		
		for(int i=0;i<100;i++){
			Thread thread = new Thread(new Runnable(){

				public void run() {
					System.out.println(HttpUtils.get("http://test4-fbtoam.pingan.com.cn:34080/btoa/portal/register/checkMobileNo?mobileNo=18285111205&deviceId=111&ffAppID=10004&ffOs=iOS&ffOsVersion=9.2&ffNativeVersion=1.0&anydoorSdkVersion=0.0.0&ffApiVersion=2&ffDeviceID=11000000"));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				
			});
			thread.start();
		}
		
	}

}
