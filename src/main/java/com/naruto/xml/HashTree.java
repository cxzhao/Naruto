package com.naruto.xml;

import java.util.LinkedHashMap;
import java.util.Map;

public class HashTree {
	private Map<String,Object> map;
	public HashTree(){
		//保证插入顺序，避免解析的时候节点顺序混乱
		map = new LinkedHashMap<String, Object>();
	}
	
	public Map<String,Object> getMap() {
		return map;
	}

	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
	
}
