package com.naruto.kafka;

import java.io.Serializable;

public interface KProducer<V extends Serializable> {
	
	/**
	 * @Title: sendSync 
	 * @Description: 同步发送 
	 * @param 
	 * @return boolean 
	 * @throws 
	 * @author zhaochenxi
	 */
	public  boolean sendSync(String topic,String key,V value);
	
	/**
	 * @Title: send 
	 * @Description: 消息发送，发送之后 
	 * @param 
	 * @return boolean 
	 * @throws 
	 * @author zhaochenxi
	 */
	public boolean send(String topic,String key,V value);
	
	public void close();
}
