package com.naruto.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Length 
 * @Description: 检查参数字符串长度
 * @author zhaochenxi
 * @date 2017年3月25日 下午3:30:23
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamLength {
	
	public int max() default 20;
	
	public int min() default 0;
}
