package com.naruto.kafka;

import java.io.Serializable;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KProducerHandle<V extends Serializable> implements KProducer<V>{
		
	private Producer<String,V> producer = null;
	
	public KProducerHandle(){
		producer = ConnPropManager.getProducer();
	}	
	
	@Override
	public  boolean sendSync(String topic, String key, V value) {
		if(producer==null){
			producer = ConnPropManager.getProducer();
		}
		
		try{
			//发送之后等待消息发送的返回值
			RecordMetadata metadata = producer.send(new ProducerRecord<String,V>(topic,key,value)).get();
			if(metadata==null){
				//发送失败
				System.out.println("消息发送失败，请做处理");
			}else{
				//消息发送成功
				//System.out.println("消息发送成功");
				//long offset = metadata.offset();
			}
		}catch(Exception e){
			System.out.println("消息队列发送数据失败key="+key);
			e.printStackTrace();
		}finally{
			//如果发送失败那么持久化该数据
		}
		return true;
	}
	
	@Override
	public boolean send(String topic, String key, V value) {
		try{
			//直接发送kafka-client会启用线程来异步发送，不用get返回数据
			producer.send(new ProducerRecord<String,V>(topic,key,value));
		}catch(Exception e){
			System.out.println("消息队列发送数据失败key="+key);
			e.printStackTrace();
		}finally{
			//如果发送失败那么持久化该数据
			//producer.close();
		}
		return true;
	}
	
	@Override
	public void close() {
		if(producer!=null){
			producer.close();
		}
	}

	
	public static void main(String[] args) {
//		KProducerHandle producer = new KProducerHandle();
//		for(int i=0;i<100;i++){
//			producer.sendSync("test","test","1234+"+i);
//		}
		
	}

	

	
}
