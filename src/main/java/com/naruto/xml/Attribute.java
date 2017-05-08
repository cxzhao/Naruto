package com.naruto.xml;

import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName: Attribute 
 * @Description: xml标签属性
 * @author zhaochenxi
 * @date 2016年11月24日 下午2:59:10
 */
public class Attribute {
	private Map<String,String> map;
	
    public Attribute(){
    	map = new HashMap<String,String>();
    }
	
	public Map<String,String> getMap() {
		return map;
	}

	public void setMap(Map<String,String> map) {
		this.map = map;
	}
	
}
