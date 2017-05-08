package com.naruto.rabbitmq;

import java.io.IOException;
import com.naruto.utils.ByteUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumer {

	//简单的发送与接收，没有特别的处理
	public static final int RECEIVE_SINGLE = 0;
	//单发送多接收
	public static final int RECEIVE_MULT = 1;
	
	private Channel channel;
	public Consumer(Connection conn){
		if(conn!=null){
			try {
				channel = conn.createChannel();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void receive(int type){
		
	}
	
	@SuppressWarnings("unchecked")
	public void dealMsg(byte [] bytes){
		Message<String> msg = (Message<String>) ByteUtils.byteToObject(bytes);
		System.out.println(msg.getMsg());
	}
	
	public void getMsgSingle(String queueName){
		QueueingConsumer consumer = new QueueingConsumer(channel);
	    try {
			channel.basicConsume(queueName, true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    while (true) {
	        QueueingConsumer.Delivery delivery;
			try {
				delivery = consumer.nextDelivery();
				dealMsg(delivery.getBody());
				Thread.sleep(1);
			} catch (ShutdownSignalException e) {
				e.printStackTrace();
			} catch (ConsumerCancelledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	        
	      }
	}
}
