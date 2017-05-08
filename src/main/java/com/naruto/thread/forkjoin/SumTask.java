package com.naruto.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @ClassName: SumTask 
 * @Description: 1+2+3+....+1000000000; 
 * @author zhaochenxi
 * @date 2016年9月16日 下午5:36:21
 */
public class SumTask extends RecursiveTask<Long>{

	private static final long serialVersionUID = -7783271832811605719L;

	private static final int THRESHOLD = 10000000;//分割阀值
	
	private long start;
	private long end;
	
	public SumTask(Long start,Long end){
		this.start=start;
		this.end=end;
	}
	
	@Override
	protected Long compute() {
		long sum=0L;
		if((start-end)<THRESHOLD){
			//执行任务
			for(long i = start; i< end;i++){  
                sum += i;  
            } 
		}else{
			//继续分割任务，然后执行
			Long mid = (start+end)/2;
			SumTask left = new SumTask(start,mid);
			SumTask right = new SumTask(mid,end);
			left.fork();
			right.fork();
			sum = left.join()+right.join();
		}
		return sum;
	}

	
	
}
