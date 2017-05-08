package com.naruto.annotation;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName("zhaochenxi");
		user.setPhone("18285111145");
		
		ParamLengthHandle.handle(User.class);
	}

}
