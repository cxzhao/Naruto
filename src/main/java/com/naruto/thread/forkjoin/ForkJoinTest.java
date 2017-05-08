package com.naruto.thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinTest {

	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		ForkJoinPool forkJoinPool = new ForkJoinPool();  
	    Future<Long> result = forkJoinPool.submit(new SumTask(0L, 100000000L));
	    try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	    long time2 = System.currentTimeMillis()-time1;
	    System.out.println("time1="+time2);
	    
	    long time3 = System.currentTimeMillis();
	    long sum=0;
	    for(long i=0L;i<100000000L;i++){
	    	sum+=i;
	    }
	    System.out.println(sum);
	    
	    long time4 = System.currentTimeMillis()-time3;
	    System.out.println("time2="+time4);
	}

}
