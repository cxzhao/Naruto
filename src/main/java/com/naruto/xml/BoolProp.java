package com.naruto.xml;

public class BoolProp {
	private Attribute attr;
	private boolean value;
	
	public BoolProp(){
		attr = new Attribute();
	}
	
	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}	
}
