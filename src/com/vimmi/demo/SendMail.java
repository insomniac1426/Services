package com.vimmi.demo;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator; 

public class SendMail {

	public static void main(String[] args) {
		final String loginTeamUsername = "supplychainmgmt5";
		final String loginTeamPassword = "password321";
		final String sendToUser = "vimmi128@gmail.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication p= 	new PasswordAuthentication(loginTeamUsername,loginTeamPassword );
				//System.out.println("hey username is" +p.getUserName());
				return p;						
				}					
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(loginTeamUsername));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(sendToUser));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	

}
