package com.naruto.xml;

import java.util.List;
/**
 * @ClassName: TestPlan 
 * @Description: Jmeter测试计划
 * @author zhaochenxi
 * @date 2016年11月24日 下午2:58:39
 */
public class TestPlan {
	
	private List<BoolProp> boolProp;
	private Attribute attr;
	private List<StringProp> stringProp;
	private ElementProp elementProp;
	
	public TestPlan(){
		setAttr(new Attribute());
	}	
 
	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public List<BoolProp> getBoolProp() {
		return boolProp;
	}

	public void setBoolProp(List<BoolProp> boolProp) {
		this.boolProp = boolProp;
	}

	public List<StringProp> getStringProp() {
		return stringProp;
	}

	public void setStringProp(List<StringProp> stringProp) {
		this.stringProp = stringProp;
	}

	public ElementProp getElementProp() {
		return elementProp;
	}

	public void setElementProp(ElementProp elementProp) {
		this.elementProp = elementProp;
	}



}
