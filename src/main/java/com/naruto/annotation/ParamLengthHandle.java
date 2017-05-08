package com.naruto.annotation;

import java.lang.reflect.Field;

/**
 * @ClassName: ParamLengthHandle 
 * @Description: 参数长度处理器
 * @author zhaochenxi
 * @date 2017年3月25日 下午4:59:59
 */
public class ParamLengthHandle {
	
	public static void handle(Class<?> c){
		Field [] fields = c.getDeclaredFields();
		
		for(Field field : fields){
			ParamLength [] paramLength=field.getAnnotationsByType(ParamLength.class);
			if(paramLength!=null){
				for(ParamLength length:paramLength){
					int max = length.max();
					int min = length.min();
					System.out.println(field.getName()+":max="+max+",min="+min);
				}
			}
			
		}
	}
}
