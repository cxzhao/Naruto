package com.naruto.eight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		List<String> list = Stream.of("a","b","c").map(a->a.toUpperCase()).collect(Collectors.toList());
		for(String s:list){
			System.out.println(s);
		}

		int max = Stream.of(1,3,4,7,2,9).max(Comparator.comparing(a->a)).get();
		System.out.println(max);
		
		List<Integer> list2 = Stream.of(17,6,1,4,10,6,7).collect(Collectors.toList());
		Collections.sort(list2,(o1,o2)->o1.compareTo(o2));
		
		for(Integer i:list2){
			System.out.println(i);
		}
		 */
	
		List<Integer> number = Stream.of(1,2,3,4,5,6,7,8,9).collect(Collectors.toList());
		//求和
		int sum1 = number.stream().reduce(0,(a,b)->(a+b));
		System.out.println("sum1="+sum1);
		
		int sum2 = number.stream().reduce(0,(a,b)->(a+b),(a,b)->(a*b));
		System.out.println("sum2="+sum2);
		
		//将数据分成奇数和偶数两个集合
		/*
		Map<Boolean,List<Integer>> mapList = number.stream().collect(Collectors.partitioningBy(e->(e%2==0)));
		List<Integer> oddList = mapList.get(false);
		oddList.forEach(e->{
			System.out.print(e+",");
		});
		System.out.println("");
		List<Integer> evenList = mapList.get(true);
		evenList.forEach(e->{
			System.out.print(e+",");
		});*/
		
		//数据分组
		/*Map<Boolean,List<Integer>> mapList = number.stream().collect(Collectors.groupingBy(e->(e%2==0)));
		List<Integer> oddList = mapList.get(false);
		oddList.forEach(e->{
			System.out.print(e+",");
		});
		System.out.println("");
		List<Integer> evenList = mapList.get(true);
		evenList.forEach(e->{
			System.out.print(e+",");
		});*/
		
		/*
		List<Person> personList = Stream.of(
				new Person("zhangshan","male","guiyang"),
				new Person("lisi","male","guiyang"),
				new Person("wangwu","male","guiyang"),
				new Person("zhangsi","female","beijing"),
				new Person("wangliu","female","beijing"),
				new Person("houzi","female","beijing"),
				new Person("xulaoer","male","shengzheng"),
				new Person("huangbeibei","male","shengzheng"),
				new Person("liulaoshan","male","shengzheng")).collect(Collectors.toList());
		Map<String,List<Person>> mapList = personList.stream().collect(Collectors.groupingBy(Person::getCity));
		List<Person> bList = mapList.get("beijing");
		bList.forEach(e->{
			System.out.println(e.getName());
		});
		
		String names = personList.stream().map(Person::getName).collect(Collectors.joining(",","name={","}"));
		
		System.out.println(names);
		
		Map<String,Long> countList = personList.stream().collect(Collectors.groupingBy(Person::getCity,Collectors.counting()));
	  */
		
	   Thread thread = new Thread(()->{
		   System.out.println("hello world!");
	   });
	   thread.start();
	   
	   
	}

}
