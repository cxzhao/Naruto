package com.naruto.annotation;

public class User {

	@ParamLength(max=10,min=1)
	private String name;
	
	@ParamLength(max=11,min=11)
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
