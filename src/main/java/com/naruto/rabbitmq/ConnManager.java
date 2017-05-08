package com.naruto.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName: ConnManager 
 * @Description: TODO(MQ连接管理器) 
 * @author zhaochenxi
 * @date 2016年11月21日 上午11:26:50
 */
public class ConnManager {

	private static String userName="guest";
	private static String password="guest";
	private static String virtualHost = "127.0.0.1";
	private static String hostName = "127.0.0.1";
	private static int portNumber = 5672;
	
	public static ConnectionFactory factory = null;
	public static Connection conn = null;
	
	static{
		factory = new ConnectionFactory();
		//factory.setUsername(userName);
		//factory.setPassword(password);
		//factory.setVirtualHost(virtualHost);
		factory.setHost(hostName);
		factory.setPort(portNumber);
		try {
			conn = factory.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	
}
