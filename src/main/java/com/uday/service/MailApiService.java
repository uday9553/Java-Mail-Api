package com.uday.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailApiService {
	
	public String getMessage() {
		return "hello from service";
	}
	
	public String sendMail(String myEmail,String password,String recepient) {
		System.out.println("sendMail starting");
		Properties props=new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session=Session.getInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myEmail,password);
			}
		}); 
		Message message=prepareMessage(session,myEmail,recepient);
		try {
			Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("message failed to sent");
			e.printStackTrace();
			return e.getMessage();
		}
		return "message sent successfully";
	}

	private Message prepareMessage(Session session, String myEmail, String recepient) {
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("message from java application");
			message.setText("This message came from java application");
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} 
		
		return null;
	}

}
