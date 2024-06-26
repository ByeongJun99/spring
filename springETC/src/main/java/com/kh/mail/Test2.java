package com.kh.mail;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class Test2 {
	
	@Autowired
	private JavaMailSender sender;
	
	@GetMapping("send")
	public String mail() {
		
		// 메시지 생성
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("이메일 전송 테스트2");
		message.setText("이메일을 테스트하도록 하겠습니다.");
		
		String[] to = {"jo__y@naver.com"};
		message.setTo(to);
		
		String[] cc = {"jo__y@naver.com"};
		message.setCc(cc);
		
		sender.send(message);
		
		return "redirect:/";
	}
	
	@GetMapping("hypermail")
	public String hyperMail() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		
		String[] to = {"jo__y@naver.com"};
		helper.setTo(to);
		
		String[] cc = {"jo__y@naver.com"};
		helper.setCc(cc);
		
		helper.setSubject("이메일 전송테스트3");
		
		String url = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.port(8888).path("./user")
				.queryParam("user_id", "qndqnd2232")
				.queryParam("uuid", "jdksalfsldkf")
				.toUriString();
		
		// 두번째 인자를 true로 보낼 시 html을 사용하겠다라는 의미
		helper.setText("<a href='"+url+"'>웹사이트로 이동</a>", true);
		sender.send(message);
		
		return "redirect:/";
	}
	
	@GetMapping("sendFile")
	public String sendFile() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		String[] to = {"jo__y@naver.com"};
		helper.setTo(to);
		
		String[] cc = {"jo__y@naver.com"};
		helper.setCc(cc);
		
		helper.setSubject("이미지 전송 이메일 테스트");
		
		helper.setText("파일 전송 테스트입니다.");
		
		// 첨부파일 추가
		DataSource source = new FileDataSource("C:\\Users\\user1\\Desktop\\타노스.jpg");
		helper.addAttachment(source.getName(), source);
		
		sender.send(message);
		
		return "redirect:/";
	}
}
