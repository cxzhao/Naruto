package com.naruto.xml;

public class StringProp {
	
	private Attribute attr;
	private String value;
	
	public StringProp(){
		attr = new Attribute();
	}
	
	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
