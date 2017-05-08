package com.naruto.proxy;

public interface BAHandle {
	/**
	 * @Title: before 
	 * @Description: 前置处理器 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	public void before();
	/**
	 * 
	 * @Title: after 
	 * @Description: 后置处理器 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	public void after();
}
