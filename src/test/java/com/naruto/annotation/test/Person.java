package com.naruto.annotation.test;

import com.naruto.annotation.Params;

public class Person {

	@Params("zhaochenxi")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
