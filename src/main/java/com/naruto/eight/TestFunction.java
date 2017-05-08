package com.naruto.eight;
/**
 * @ClassName: TestFunction 
 * @Description: 函数接口，该接口接受一个参数，然后返回一个bool值
 * @author zhaochenxi
 * @date 2017年4月8日 下午10:29:29
 */
@FunctionalInterface
public interface TestFunction<T> {
	/**
	 * @Title: test 
	 * @Description: 如果T符合某种条件则返回true 
	 * @param 
	 * @return boolean 
	 * @throws 
	 * @author zhaochenxi
	 */
	boolean test(T t);
	
}
