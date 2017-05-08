package com.naruto.eight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Lambda表达式
 * @ClassName: LambdaStudy 
 * @Description: 
 * @author zhaochenxi
 * @date 2017年3月16日 下午10:58:18
 */
public class LambdaStudy {
	
	public static void hello(String s){
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.forEach(e->{
			System.out.println(e);
		});
		
	}

}
