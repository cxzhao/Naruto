package com.naruto.xml;

import java.util.List;

/**
 * @ClassName: HTTPSamplerProxy 
 * @Description: Jmeter请求
 * @author zhaochenxi
 * @date 2016年11月25日 上午11:21:20
 */
public class HTTPSamplerProxy {
	
	private Attribute attr;
	private ElementProp elementProp;
	private List<StringProp> stringProp;
	private List<BoolProp> boolProp;
	
	public HTTPSamplerProxy(){
		setAttr(new Attribute());
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
	public List<BoolProp> getBoolProp() {
		return boolProp;
	}
	public void setBoolProp(List<BoolProp> boolProp) {
		this.boolProp = boolProp;
	}
	public Attribute getAttr() {
		return attr;
	}
	public void setAttr(Attribute attr) {
		this.attr = attr;
	}
	
	
}
