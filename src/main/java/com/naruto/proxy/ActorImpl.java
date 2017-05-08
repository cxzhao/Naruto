package com.naruto.proxy;

public class ActorImpl implements Actor,BAHandle {

	@Override
	public void acceptMovie() {
		System.out.println("演员接受了这部电影的邀请");
	}

	@Override
	public void before() {
		  System.out.println("助理和导演联系");  
	}

	@Override
	public void after() {
		System.out.println("助理处理合同");  		
	}

}
