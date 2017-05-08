package com.naruto.mail;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @ClassName: MailService 
 * @Description: 这个类用于发送邮件
 * @author zhaochenxi
 * @date 2016年12月9日 上午10:52:46
 */
public class MailService {

	private static final String MAIL_STAMP_HOST = "smtp.163.com";
	private static final String IMAP_SMTP_MAIL_PASS = "etkitty163";
	private static final String FROM_MAIL = "etkitty@163.com";
	
	private Session session = null;
	private MailSSLSocketFactory sf=null;
	public MailService(){
		
	}
	
	public void init(){
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		Properties props = new Properties();
	    props.put("mail.smtp.host", MAIL_STAMP_HOST);
	    props.put("mail.smtp.auth", "true");
		session = Session.getInstance(props);
		
	}
	public void sendMail(Mail mail){
		 init();
		 try {
		        MimeMessage msg = new MimeMessage(session);
		        msg.setFrom(new InternetAddress(FROM_MAIL));
		        msg.setSubject(mail.getTitle());
		        msg.setSentDate(new Date());
		        msg.setText(mail.getContext());
		        Transport transport = session.getTransport();
		        transport.connect(MAIL_STAMP_HOST, FROM_MAIL, IMAP_SMTP_MAIL_PASS);
		        transport.sendMessage(msg, new Address[]{new InternetAddress(mail.getAddress())});
		        transport.close();
		    } catch (MessagingException mex) {
		        System.out.println("send failed, exception: " + mex);
		    }
	}
	
	public static void main(String[] args) {
		Mail mail = new Mail();
		mail.setTitle("mail test");
		mail.setContext("www.zhaochenxi.com");
		mail.setAddress("704405121@qq.com");
		MailService mailService = new MailService();
		mailService.sendMail(mail);
	}
}
