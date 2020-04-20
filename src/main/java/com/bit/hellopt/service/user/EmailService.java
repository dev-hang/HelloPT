package com.bit.hellopt.service.user;

public interface EmailService {
	public void sendSimpleMessage(String to, String subject, String text);
}
