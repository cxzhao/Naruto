package com.naruto.press;

import com.naruto.utils.HttpUtils;

public class Task {

	public Task(){}
	
	public Task(String url){
		this.url=url;
	}
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void runTask(){
		TMonitor.requestSendCount++;
		System.out.println(TMonitor.requestSendCount);
		while(true){
			HttpUtils.get(url);
		}	
	} 
}
