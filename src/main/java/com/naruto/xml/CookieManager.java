package com.naruto.xml;

import java.util.List;

/**
 * @ClassName: CookieManager 
 * @Description: Jmeter Cookie管理器
 * @author zhaochenxi
 * @date 2016年11月25日 上午11:14:13
 */
public class CookieManager {
	private Attribute attr;
	private CollectionProp collectionProp;
	private List<BoolProp> boolProp;
	
	public CookieManager(){
		setAttr(new Attribute());
	}
	
	public Attribute getAttr() {
		return attr;
	}
	public void setAttr(Attribute attr) {
		this.attr = attr;
	}
	public CollectionProp getCollectionProp() {
		return collectionProp;
	}
	public void setCollectionProp(CollectionProp collectionProp) {
		this.collectionProp = collectionProp;
	}
	public List<BoolProp> getBoolProp() {
		return boolProp;
	}
	public void setBoolProp(List<BoolProp> boolProp) {
		this.boolProp = boolProp;
	}
	
	
}
