package com.naruto.kafka;

import com.naruto.utils.ConfigReader;

public class KafkaConfig {
	
	public static String ZOOKEEPER_CONN = null;
	public static String SERIALIZER_CLASS = null;
	public static String BOOTSTRAP_SERVERS=null;
	
	public static String ACKS = null;
	public static int RETRIES = 0;
	public static int BATCH_SIZE = 1024;
	public static int LINGER_MS = 1;
	public static int BUFFER_MEMORY = 33554432;
	
	public static String ENABLE_AUTO_COMMIT=null;
	public static String AUTO_COMMIT_INTERVAL_MS=null;
	public static String SESSION_TIMEOUT_MS=null;
	public static String DEFAULT_GROUP_ID=null;
	
	static {
		ConfigReader config = new ConfigReader("kafka.properties");		
		BOOTSTRAP_SERVERS = config.getString("bootstrap.servers");	
		ZOOKEEPER_CONN = config.getString("zookeeper.connect");
		SERIALIZER_CLASS = config.getString("serializer.class");
		ACKS = config.getString("producer.acks");
		RETRIES = Integer.valueOf(config.getString("producer.retries"));
		BATCH_SIZE = Integer.valueOf(config.getString("producer.batch.size"));
		LINGER_MS = Integer.valueOf(config.getString("producer.linger.ms"));
		BUFFER_MEMORY = Integer.valueOf(config.getString("producer.buffer.memory"));
		
		ENABLE_AUTO_COMMIT=config.getString("consumer.enable.auto.commit");
		AUTO_COMMIT_INTERVAL_MS=config.getString("consumer.auto.commit.interval.ms");
		SESSION_TIMEOUT_MS=config.getString("consumer.session.timeout.ms");
		DEFAULT_GROUP_ID=config.getString("consumer.default.group.id");
	}
}
