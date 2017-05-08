package com.naruto.eight;

import java.util.ArrayList;
import java.util.List;

public class CreateArray {

	public List<Integer> getArray(IOperator op){
		List<Integer> resArray = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			resArray.add(op.createArrray(i));
		}
		return resArray;
	}
	
	public static void main(String[] args) {
		
//		CreateArray c = new CreateArray();
//		List<Integer> list = c.getArray(new IOperator() {
//			
//			@Override
//			public int createArrray(int i) {
//
//				return i*3;
//			}
//		});
//		for(int i=0;i<10;i++){
//			System.out.println(list.get(i));
//		}
	}

}
