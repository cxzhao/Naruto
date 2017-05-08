package com.naruto.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaTest {
	
	/**
	 * 一个消费者组内多个一条消息只有一个消费者能消费，不同的消费者组内的消费者可以消费同一条消息，这是一种
	 * 发布-订阅模式
	 * @Title: test1 
	 * @Description: TODO 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	public static void test1(){
		Thread pthread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				KProducer<String> producer = new KProducerHandle<String>();	
				int i=0;
				boolean flag = true;
				while(flag){
					if(i==1000){
						flag=false;
					}
					producer.sendSync("topic-test-2","key-test-1","test2-message-"+i);
					i++;
				}
				producer.close();				
			}
		});
		pthread.start();
		KConsumer consumer1 = new KConsumerHandle();
		consumer1.handle("topic-test-2","test-1", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-1,consumer=1,"+record.value());
				return false;
			}
		});
		KConsumer consumer2 = new KConsumerHandle();
		consumer2.handle("topic-test-2","test-1", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-1,consumer=2,"+record.value());
				return false;
			}
		});
		KConsumer consumer3 = new KConsumerHandle();
		consumer3.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=3,"+record.value());
				return false;
			}
		});
		KConsumer consumer4 = new KConsumerHandle();
		consumer4.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=4,"+record.value());
				return false;
			}
		});
	}

	/**
	 * 同一个消费者组内一个分区被一个消费者消费，一个消费者可以消费多个分区内的消息，
	 * 消费者数量多于分区数量的时候会导致多于的消费者没有消息可以消费
	 * @Title: test2 
	 * @Description: TODO 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	public static void test2(){
		KConsumer consumer1 = new KConsumerHandle();
		consumer1.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=1,"+record.value());
				return false;
			}
		});
		KConsumer consumer2 = new KConsumerHandle();
		consumer2.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=2,"+record.value());
				return false;
			}
		});
		KConsumer consumer3 = new KConsumerHandle();
		consumer3.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=3,"+record.value());
				return false;
			}
		});
		KConsumer consumer4 = new KConsumerHandle();
		consumer4.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=4,"+record.value());
				return false;
			}
		});
		KConsumer consumer5 = new KConsumerHandle();
		consumer5.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=5,"+record.value());
				return false;
			}
		});
		KConsumer consumer6 = new KConsumerHandle();
		consumer6.handle("topic-test-2","test-2", new ConsumerHandleCallback() {
			
			@Override
			public boolean execute(ConsumerRecord<Object, Object> record) {
				System.out.println("groupId=test-2,consumer=6,"+record.value());
				return false;
			}
		});
		
	}
	public static void main(String[] args) {
		//test1();
		Thread pthread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				KProducer<String> producer = new KProducerHandle<String>();	
				int i=0;
				boolean flag = true;
				while(flag){
					if(i==1000){
						flag=false;
					}
					producer.send("topic-test-2","key-test-"+i,"test2-message-"+i);
					i++;
				}
				producer.close();				
			}
		});
		pthread.start();
		test2();
	}

}
