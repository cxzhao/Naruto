package com.naruto.press;

/**
 * @ClassName: 任务处理线程 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhaochenxi
 * @date 2017年3月9日 下午2:43:01
 */
public class TaskThread implements Runnable {

	public String threadId;
	
	private Task task;
	
	public TaskThread(Task task,String threadId){
		this.task=task;
		this.threadId=threadId;
	}
	
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		task.runTask();
		long time=System.currentTimeMillis()-start;
		//System.out.println("ThreadTime="+time);
	}

}
