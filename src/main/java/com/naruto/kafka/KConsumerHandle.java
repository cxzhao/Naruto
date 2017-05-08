package com.naruto.kafka;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KConsumerHandle implements KConsumer{

	private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
	
	private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,4,1,TimeUnit.HOURS, workQueue);
		
	public volatile boolean isRun = true;
	
	@Override
	public void handle(final String topic,final String groupId,ConsumerHandleCallback handle) {

		threadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				KafkaConsumer<Object, Object> consumer = ConnPropManager.getConsumer(groupId);
				if(consumer==null){
					return;
				}
				consumer.subscribe(Arrays.asList(topic));
				while(isRun){
					ConsumerRecords<Object, Object> records = consumer.poll(10000);					
					for (ConsumerRecord<Object, Object> record : records){
						if(!handle.execute(record)){
							//TODO 如果处理失败，做出相应的处理，如持久化处理失败的消息							
						}
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				consumer.close();
			}
		});
	}
	
	public static void main(String[] args) {
		KConsumerHandle handle = new KConsumerHandle();
		handle.handle("test","test", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println(record.value());
				return true;
			}
		});
	}

}
