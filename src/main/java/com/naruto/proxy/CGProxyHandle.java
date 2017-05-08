package com.naruto.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @ClassName: CGProxyHandle 
 * @Description: CGLib动态代理类,直接对类进行代理，委托类不需要实现接口
 * @author zhaochenxi
 * @date 2017年4月13日 下午9:44:36
 */
public class CGProxyHandle implements MethodInterceptor {

	private Enhancer enhancer;
	
	public Object bind(Class<?> clazz){
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("=========before==========");
		Object result = arg3.invokeSuper(arg0, arg2);
		System.out.println("=========after===========");
		return result;
	}

}
