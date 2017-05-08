package com.naruto.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: ProxyHandler 
 * @Description: 动态代理实现类，用于生成代理类
 * @author zhaochenxi
 * @date 2017年4月13日 下午3:01:31
 */
public class ProxyHandler<T extends BAHandle> implements InvocationHandler {
   
	private T target;
	 	
	@SuppressWarnings("unchecked")
	public Object bind(Object target) { 
		
        this.target = (T) target;  
        //取得代理对象  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);  
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {		
		Object result=null;
		target.before();
        //执行方法  
        result=method.invoke(target, args);
        target.after();
        return result; 
	}


}
