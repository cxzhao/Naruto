package com.naruto.xml;

import java.util.List;

public class ThreadGroup {
	private List<BoolProp> boolProp;
	private Attribute attr;
	private List<StringProp> stringProp;
	private List<LongProp> longProp;
	private ElementProp elementProp;
	
	public ThreadGroup(){
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

	public List<LongProp> getLongProp() {
		return longProp;
	}

	public void setLongProp(List<LongProp> longProp) {
		this.longProp = longProp;
	}

	public ElementProp getElementProp() {
		return elementProp;
	}

	public void setElementProp(ElementProp elementProp) {
		this.elementProp = elementProp;
	}

}
