package com.naruto.press;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: Monitor 
 * @Description: 线程监视器，用于监视当前程序创建的所有线程的状态
 * @author zhaochenxi
 * @date 2017年3月9日 下午4:43:35
 */
public class TMonitor implements Runnable{

	public static Logger logger = LoggerFactory.getLogger(TMonitor.class);
	public static LinkedBlockingQueue<Thread> threadQueue = new LinkedBlockingQueue<Thread>();;

	//线程状态
	//新建
	public static Set<String> newSet=new HashSet<String>();
	//可运行状态,可能正在运行也可能没有运行
	public static Set<String> runnableSet=new HashSet<String>();
	//被阻塞
	public static Set<String> blockedSet=new HashSet<String>();
	//等待
	public static Set<String> waitSet=new HashSet<String>();
	//计时等待
	public static Set<String> timeWaitingSet=new HashSet<String>();
	//被终止
	public static Set<String> terminatedSet=new HashSet<String>();
	
	//已经发送的请求
	public static volatile int requestSendCount = 0;
	
//	public TMonitor(){
//		threadQueue = new LinkedBlockingQueue<Thread>();
//	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread thread = threadQueue.take();
				String state = thread.getState().toString();
				updateThreadStateCount(state,thread.getName());
				//终止的线程出队之后就不要了
				if(!"TERMINATED".equals(state)){
					threadQueue.put(thread);	
				}
				print();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * @Title: updateThreadStateCount 
	 * @Description: 修改当前线程状态统计数据 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	public void updateThreadStateCount(String state,String threadName){
		switch (state) {
		case "NEW":
			newSet.add(threadName);
			break;
		case "RUNNABLE":
			runnableSet.add(threadName);
			blockedSet.remove(threadName);
			waitSet.remove(threadName);
			timeWaitingSet.remove(threadName);			
			break;
		case "BLOCKED":
			blockedSet.add(threadName);
			runnableSet.remove(threadName);
			waitSet.remove(threadName);
			timeWaitingSet.remove(threadName);
			break;			
		case "WAITING":
			waitSet.add(threadName);
			blockedSet.remove(threadName);
			runnableSet.remove(threadName);
			timeWaitingSet.remove(threadName);
			break;			
		case "TIMED_WAITING":
			timeWaitingSet.add(threadName);
			waitSet.add(threadName);
			blockedSet.remove(threadName);
			runnableSet.remove(threadName);
			break;
		case "TERMINATED":
			terminatedSet.add(threadName);
			blockedSet.remove(threadName);
			runnableSet.remove(threadName);
			waitSet.remove(threadName);
			timeWaitingSet.remove(threadName);
			break;			
		default:
			break;
		}
	}
	
	public void print(){	
		logger.info("SEND="+requestSendCount+",NEW="+newSet.size()+",RUNNABLE="+runnableSet.size()+",BLOCKED="+blockedSet.size()+",WAITING="+waitSet.size()+",TIMED_WAITING="+timeWaitingSet.size()+",TERMINATED="+terminatedSet.size());
	}
	
	public String getState(){
		return "NEW="+newSet.size()+",RUNNABLE="+runnableSet.size()+",BLOCKED="+blockedSet.size()+",WAITING="+waitSet.size()+",TIMED_WAITING="+timeWaitingSet.size()+",TERMINATED="+terminatedSet.size();
	}
	
}
