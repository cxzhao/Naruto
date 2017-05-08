package com.naruto.xml;

import java.util.List;

/**
 * @ClassName: ElementProp 
 * @Description: jmeter用户自定义的内容，如参数
 * @author zhaochenxi
 * @date 2016年11月24日 下午3:07:21
 */
public class ElementProp {
	
	private Attribute attr;
	private List<StringProp> stringProp;
	private List<BoolProp> boolProp;
	private List<LongProp> longProp;
	private CollectionProp collectionProp;
		
	public List<StringProp> getStringProp() {
		return stringProp;
	}

	public void setStringProp(List<StringProp> stringProp) {
		this.stringProp = stringProp;
	}

	public List<BoolProp> getBoolProp() {
		return boolProp;
	}

	public void setBoolProp(List<BoolProp> boolProp) {
		this.boolProp = boolProp;
	}

	public List<LongProp> getLongProp() {
		return longProp;
	}

	public void setLongProp(List<LongProp> longProp) {
		this.longProp = longProp;
	}

	public CollectionProp getCollectionProp() {
		return collectionProp;
	}

	public void setCollectionProp(CollectionProp collectionProp) {
		this.collectionProp = collectionProp;
	}

	public ElementProp(){
		attr = new Attribute();
	}
	
	public Attribute getAttr() {
		
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}


}
