package ua.lviv.lgs.shared;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.impl.BucketDaoImpl;

public class MailSender {

	private static Logger log = LogManager.getLogger(BucketDaoImpl.class.getName());

	private final static String from = "info@i-shop.com";
	private final static String username = "u91127";
	private final static String password = "0a61fd20";

	private static MailSender ms;
	private static Properties prop = new Properties();

	private MailSender() {
		prop.put("mail.smtp.host", "smtp-5.1gb.ua");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	}

	public static MailSender getMailSender() {
		if (ms == null) {
			ms = new MailSender();
		}
		return ms;
	}

	public void sendMail(String to, String subject, String text) {
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);
		} catch (MessagingException e) {
			log.error("Can`t send mail: ", e);
		}
	}

}
