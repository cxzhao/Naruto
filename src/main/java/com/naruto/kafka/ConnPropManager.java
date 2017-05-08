package com.naruto.kafka;

import java.util.Properties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @ClassName: ConnManager
 * @Description: 
 * @author zhaochenxi
 * @date 2016年11月21日 上午11:26:50
 */
public class ConnPropManager {

	public static Properties producerProp = null;
	
	public static Properties consumerProp = null;
	
	static{
		 producerProp = new Properties();
		 producerProp.put("bootstrap.servers", KafkaConfig.BOOTSTRAP_SERVERS);
		 producerProp.put("acks", KafkaConfig.ACKS);
		 producerProp.put("retries",KafkaConfig.RETRIES);
		 producerProp.put("batch.size", KafkaConfig.BATCH_SIZE);
		 producerProp.put("linger.ms",KafkaConfig.LINGER_MS);
		 producerProp.put("buffer.memory", KafkaConfig.BUFFER_MEMORY);
		 producerProp.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 producerProp.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 
		 consumerProp = new Properties();
		 consumerProp.put("bootstrap.servers", KafkaConfig.BOOTSTRAP_SERVERS);
		 consumerProp.put("group.id",KafkaConfig.DEFAULT_GROUP_ID);
		 consumerProp.put("enable.auto.commit",KafkaConfig.ENABLE_AUTO_COMMIT);
		 //consumerProp.put("auto.commit.interval.ms",KafkaConfig.AUTO_COMMIT_INTERVAL_MS);
		 consumerProp.put("session.timeout.ms", KafkaConfig.SESSION_TIMEOUT_MS);
		 consumerProp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		 consumerProp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	 }
     
	
	 public  static <K,V> KafkaProducer<K,V> getProducer(){
		 if(ConnPropManager.producerProp==null){
			 System.out.println("--------------------");
		 }
		 return new KafkaProducer<K, V>(ConnPropManager.producerProp);
	 } 
	
	 public  static <K,V> KafkaConsumer<K,V> getConsumer(String groupId){
		 if(groupId!=null || "".equals(groupId)){
			 Properties prop =  new Properties();
			 prop.put("bootstrap.servers", KafkaConfig.BOOTSTRAP_SERVERS);
			 prop.put("group.id",groupId);
			 prop.put("enable.auto.commit",KafkaConfig.ENABLE_AUTO_COMMIT);
			 //consumerProp.put("auto.commit.interval.ms",KafkaConfig.AUTO_COMMIT_INTERVAL_MS);
			 prop.put("session.timeout.ms", KafkaConfig.SESSION_TIMEOUT_MS);
			 prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			 prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			 return new KafkaConsumer<K, V>(prop);
		 }else{
			 return null;
		 }		 
	 } 
	 
	 public  static <K,V> KafkaConsumer<K,V> getConsumer(){	
		 return new KafkaConsumer<K, V>(ConnPropManager.consumerProp);		 
	 } 
	
}
