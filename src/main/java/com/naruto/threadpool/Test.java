package com.naruto.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service=Executors.newFixedThreadPool(5);
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
