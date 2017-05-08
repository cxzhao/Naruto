package com.naruto.xml;

import java.util.List;

/**
 * @ClassName: ConfigTestElement 
 * @Description:Jmeter配置类，可用于配置请求默认值
 * @author zhaochenxi
 * @date 2016年11月25日 上午11:06:07
 */
public class ConfigTestElement {
	
	private Attribute attr;
	private ElementProp elementProp;
	private List<StringProp> stringProp;
	
	public ConfigTestElement(){
		setAttr(new Attribute());
	}
	
	public Attribute getAttr() {
		return attr;
	}
	public void setAttr(Attribute attr) {
		this.attr = attr;
	}
	public ElementProp getElementProp() {
		return elementProp;
	}
	public void setElementProp(ElementProp elementProp) {
		this.elementProp = elementProp;
	}
	public List<StringProp> getStringProp() {
		return stringProp;
	}
	public void setStringProp(List<StringProp> stringProp) {
		this.stringProp = stringProp;
	}
	
	
}
