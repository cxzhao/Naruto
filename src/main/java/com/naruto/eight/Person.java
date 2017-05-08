package com.naruto.eight;

public class Person {

	private String name;
	private String sex;
	private String city;
	
	public Person(){
		
	}
	public Person(String name,String sex,String city){
		setCity(city);
		setName(name);
		setSex(sex);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
