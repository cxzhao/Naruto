package com.naruto.thread.volatiletest;

public class VolatileFeaturesExample1 {

	volatile long v1=0L;
	
	public void set(long l){
		v1=1;
	}
	
	public void incr(){
		v1++;
	}
	
	public long get(){
		return v1;
	}
}
