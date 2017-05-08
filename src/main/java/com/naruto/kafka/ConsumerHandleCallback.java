package com.naruto.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @ClassName: ConsumerHandleCallback 
 * @Description: 消息处理回调接口
 * @author zhaochenxi
 * @date 2017年4月27日 上午11:20:28
 */
public interface ConsumerHandleCallback {

	public boolean execute(ConsumerRecord<Object, Object> record);
	
}
