package com.web.curation.member;

import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	public void sendMail(String from, String To, String subject, String text) throws MessagingException;
}
