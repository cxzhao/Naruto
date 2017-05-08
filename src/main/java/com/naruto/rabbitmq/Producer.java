package com.naruto.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

	private int prodecerId;
	private Channel channel;
	private String exchangeName;
	private String queueName;
	
	public Producer(Connection conn){
		if(conn!=null){
			try {
				channel = conn.createChannel();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//初始化生产者
	public void init(String queueName,String exchangeName){
		this.queueName = queueName;
		this.exchangeName = exchangeName;
	}
	
	public void sendMsg(Message<String> msg){
		try {
			if(channel!=null){
				channel.exchangeDeclare(exchangeName, msg.getExchangeType(), true);
				channel.queueBind(queueName, exchangeName, msg.getRoutingKey());
				channel.basicPublish(exchangeName, msg.getRoutingKey(), null, msg.getMsgBytes());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getProdecerId() {
		return prodecerId;
	}

	public void setProdecerId(int prodecerId) {
		this.prodecerId = prodecerId;
	}
	
	public void close(){
		if(channel!=null){
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Producer producer = new Producer(ConnManager.conn);
		producer.init("testQueue", "testEx");
		Message<String> msg = new Message<String>();
		msg.setExchangeType("direct");
		msg.setRoutingKey("test");
		msg.setRoutingKey("test");
		msg.setMsg("ffjdafjdajfjldajfldajfda");
		producer.sendMsg(msg);
	}
}
