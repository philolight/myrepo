package com.lge.framework.ceasar.mail;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.Test;

public class MailSendTest {
	@Test
	public void testMailSend() {
		final String username = "sy3.lee";
		final String password = "sy3leelgecom";
		
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "lgekrhqmh01.lge.com"); // 회사 SMTP 서버 주소	
		
		// 위 환경 정보로 "메일 세션"을 만들고 빈 메시지를 만든다		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sy3.lee@lge.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sy3.lee@lge.com"));

			Calendar c = Calendar.getInstance();			
			String dateString = c.get(Calendar.YEAR) + "년 " + (c.get(Calendar.MONTH)+1) + "월 " + c.get(Calendar.DAY_OF_MONTH) + "일";
			message.setSubject(dateString + " 회의실 예약 취소 목록", "utf-8");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");
			
			MimeMultipart multipart = new MimeMultipart();
			
		    MimeBodyPart part = new MimeBodyPart();
		    part.setContent("안녕하십니까, B2B솔루션센터 SSD 팀 손응석 선임입니다.\r\n" + 
		    		"\r\n" + 
		    		"김효진 선임님,\r\n" + 
		    		"요청하신 취소 회의목록 보내 드립니다.\r\n\r\n\r\n" +
		    		"감사합니다." +
		    		"손응석 드림.\r\n"
		    		, "text/html; charset=utf-8");
		    multipart.addBodyPart(part);
		     
		    File file = new File("./textMailSend.txt");
		    try {
				file.createNewFile();
			} catch (IOException e) {}
		    
		    part = new MimeBodyPart();
		    FileDataSource fds = new FileDataSource("./textMailSend.txt");
		    part.setDataHandler(new DataHandler(fds));
		    part.setFileName("./textMailSend.txt");
		    multipart.addBodyPart(part);
		     
		    message.setContent(multipart);
		    
			Transport.send(message);
			
			file.delete();

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
