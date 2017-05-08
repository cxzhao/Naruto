package com.naruto.rabbitmq;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Message<T extends Serializable> {
	
	private String exchangeType;
	//255 bytes字节的长度限制
	private String routingKey;
	private T msg;

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public T getMsg() {
		return msg;
	}

	public void setMsg(T msg) {
		this.msg = msg;
	}
	
	public byte [] getMsgBytes(){
		 byte[] bytes = null;  
		    try {  
		        ByteArrayOutputStream bo = new ByteArrayOutputStream();  
		        ObjectOutputStream oo = new ObjectOutputStream(bo);  
		        oo.writeObject(msg);  		  
		        bytes = bo.toByteArray();  		  
		        bo.close();  
		        oo.close();  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
		    return bytes;
	}
}
