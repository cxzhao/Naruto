package com.naruto.xml;

import java.util.List;

public class CollectionProp {
	
	private Attribute attr;
	private List<ElementProp> elementProp;
	
	public CollectionProp(){
		attr = new Attribute();
	}
	
	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public List<ElementProp> getElementProp() {
		return elementProp;
	}

	public void setElementProp(List<ElementProp> elementProp) {
		this.elementProp = elementProp;
	}

}
