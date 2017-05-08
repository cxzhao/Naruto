package com.naruto.eight.interfaces;

public interface Singer {
	
	public void me();
	
	public default void major(){
		System.out.println("I am a singer");
	}
	
	public static ChineseSinger newInstanceChinese(){
		return new ChineseSinger();		
	}
}
