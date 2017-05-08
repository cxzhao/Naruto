package com.naruto.xml;

public class LongProp {
	private Attribute attr;
	private long value;
	
	public LongProp(){
		attr = new Attribute();
	}

	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}	
}
