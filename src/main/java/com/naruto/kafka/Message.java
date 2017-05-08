package com.naruto.kafka;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Message<T extends Serializable> implements Serializable{


	private static final long serialVersionUID = 1567487655527848L;
	
	private int msgType;
	private String title;
	private T msg;

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

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
