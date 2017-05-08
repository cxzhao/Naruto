package com.naruto.kafka;

public interface KConsumer {

	public void handle(final String topic,final String groupId,ConsumerHandleCallback call);
	
}
