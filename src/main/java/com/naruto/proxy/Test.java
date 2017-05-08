package com.naruto.proxy;

public class Test {

	public static void main(String[] args) {
		//给委托方动态生成代理
		ProxyHandler<ActorImpl> proxy = new ProxyHandler<ActorImpl>();
		Actor actor = (Actor) proxy.bind(new ActorImpl());
		//委托方执行
		actor.acceptMovie();
	}

}
