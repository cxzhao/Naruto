package com.naruto.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HashTree 
 * @Description: 测试计划子节点
 * @author zhaochenxi
 * @date 2016年11月22日 下午5:17:05
 */
public class JmeterTestPlan {
	private Map<String,Object> hashTree;	
	public JmeterTestPlan(){
		hashTree = new HashMap<String,Object>();
	}
	public Map<String,Object> getHashTree() {
		return hashTree;
	}
	public void setHashTree(Map<String,Object> hashMap) {
		this.hashTree = hashMap;
	}

}