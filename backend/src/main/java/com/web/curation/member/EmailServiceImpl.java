package com.web.curation.member;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

//	@Override
//	@Async
//	public void sendMail(String toEmail, String subject, String message) throws MessagingException {
//		System.out.println(toEmail);
//		System.out.println(subject);
//		System.out.println(message);
//		
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//
//		helper.setFrom("관리자"); // 보내는사람
//		helper.setTo(toEmail); // 받는사람
//		helper.setSubject(subject); // 메일제목
//		helper.setText(message, true); // true넣을경우 html
//
//		javaMailSender.send(mimeMessage);
//	}
	
//	public EmailServiceImpl(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
	
	@Override
	public void sendMail(String from, String to, String subject, String text) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
    	
//    	helper.setFrom("ㅁㅁ"); //보내는사람
//    	helper.setTo(to); //받는사람
//    	helper.setSubject(subject); //메일제목
//    	helper.setText(text, true); //ture넣을경우 html
    	
    	SimpleMailMessage mailMessage = new SimpleMailMessage();
    	mailMessage.setFrom("ㅁㅁ");
    	mailMessage.setTo(to);
    	mailMessage.setSubject(subject);
    	mailMessage.setText("ss");


        javaMailSender.send(mailMessage);
	}

}
