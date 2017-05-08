package com.naruto.test;


public class Test {

	public static int[] sort(int [] array){
		if(array.length==1){
			return array;
		}
		for(int i=0;i<array.length-1;i++){
			for(int j=0;j<array.length-i-1;j++){
				if(array[j]>array[j+1]){
					int tmp = array[j];
					array[j]=array[j+1];
					array[j+1]=tmp;
				}
			}
		}
		return array;
				
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String  str = "1234567890";
//		char[] strArray = str.toCharArray();
//		for(char c:strArray){
//			System.out.print(c);
//		}
//		System.out.println("");
//		for(int i=0;i<str.length();i++){
//			System.out.println(str.charAt(i));
//		}
		
//		Random r = new Random();
//		for(int i=0;i<1000;i++){
//			System.out.println((int)(Math.random()*1000));
//		}
		
		int [] array = new int[]{3,5,7,2,1,9,0,6,10,12};
		int [] res = sort(array);
		for(int i=0;i<res.length;i++){
			System.out.print(res[i]+",");
		}
		int a=3<<2;
		System.out.println(a);
	}

}
