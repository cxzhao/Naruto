package com.naruto.press;
/**
 * @ClassName: PressMain 
 * @Description: 启动1000个需要200ms，但是服务器端接受这1000个线程的访问需要1s多的时间，这期间线程应该阻塞在了发送Http请求上
 * @author zhaochenxi
 * @date 2017年3月17日 下午2:21:20
 */
public class PressMain {

	public static void main(String[] args) {
		//http://apollo.zhixingbus.com/api/query_road_point_by_id/398?appId=16624213-e765-4440-bbc6-825bd2597d03&app_token=Token&cityId=398&id=11&userId=6E5A57D8-7849-4C76-913B-5C04631B7C16&version=0
		Task task = new Task("http://api.etkitty.com/etcom/query?objectId=061222195455148007&pageNumber=1&pageSize=20");
		
		Thread mthread = new Thread(new TMonitor());
		mthread.start();
		int i=0;
		long startTime = System.currentTimeMillis();
		while(true&&i<1000){
			
			Thread thread = new Thread(new TaskThread(task,String.valueOf(i++)));
			
			try {
				TMonitor.threadQueue.put(thread);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread.start();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}

}
