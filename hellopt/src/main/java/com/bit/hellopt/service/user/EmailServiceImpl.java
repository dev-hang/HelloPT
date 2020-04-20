package com.bit.hellopt.service.user;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bit.hellopt.controller.UserController;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	public JavaMailSender emailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			
			message.setSubject(subject, "UTF-8");
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("scvgo23@gmail.com");
			helper.setTo(to);
			helper.setText(text, true);
			emailSender.send(message);
		} catch (MessagingException e) {
			logger.error("send mail error");
		}
		
	}
	
	
}
