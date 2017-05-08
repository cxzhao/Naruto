package com.naruto.thread.volatiletest;

public class VolatileFeaturesExample2 {

	long v1=0L;
	
	public synchronized void set(long l){
		v1=l;
	}
	
	public void incr(){
		long temp = get();
		temp+=1L;
		set(temp);
	}
	
	public synchronized long get(){
		return v1;
	}
	
	
	
}
