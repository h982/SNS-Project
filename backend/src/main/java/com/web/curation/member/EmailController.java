package com.web.curation.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.NormalResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private MemberService memberService;
	
	@PostMapping(value = "/send")
	@ApiOperation(value = "이메일 전송")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "이메일 정상 전송", response = NormalResponse.class)})
	public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Member member) throws MessagingException {
		Map<String, Object> resultMap = new HashMap<>();
    	HttpStatus status = null;
		StringBuffer emailcontent = new StringBuffer();
		
		emailcontent.append("<!DOCTYPE html>");
		emailcontent.append("<html>");
		emailcontent.append("<head>");
		emailcontent.append("</head>");
		emailcontent.append("<body>");
		emailcontent.append(
				" <div" 																																																	+ 
				"	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #02b875; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+ 
				"	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+ 
				"		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">혼자 머하니?</span><br />"																													+ 
				"		<span style=\"color: #02b875\">메일인증</span> 안내입니다."																																				+ 
				"	</h1>\n"																																																+ 
				"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+ 
				member.getName()																																																+
				"		님 안녕하세요.<br />"																																													+ 
				"		혼자 머하니?에 가입해 주셔서 진심으로 감사드립니다.<br />"																																						+ 
				"		아래 <b style=\"color: #02b875\">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />"																													+ 
				"		감사합니다."																																															+ 
				"	</p>"																																																	+ 
				"	<a style=\"color: #FFF; text-decoration: none; text-align: center;\""																																	+
				"	href=\"http://localhost:8080/email/certified?email=" + member.getEmail() +  "\" target=\"_blank\">"														+ 
				"		<p"																																																	+
				"			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							+ 
				"			메일 인증</p>"																																														+ 
				"	</a>"																																																	+
				"	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		+
				" </div>"
		);
		emailcontent.append("</body>");
		emailcontent.append("</html>");
		emailService.sendMail("admin", member.getEmail() + " <" + member.getEmail() + ">", "혼자 머하니 이메일 인증", emailcontent.toString());
		
		resultMap.put("message", "success");
    	status = HttpStatus.OK;
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping(value = "/certified")
	@Transactional
	@ApiOperation(value = "이메일 전송")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "이메일 정상 전송", response = NormalResponse.class)})
	public ResponseEntity<Map<String, Object>> checkEmail(String email) throws MessagingException {
		Map<String, Object> resultMap = new HashMap<>();
    	HttpStatus status = null;
    	
    	System.out.println("check");
		
		memberService.certifyMemberByEmail(email);
		Optional<Member> member = memberService.getUser(email);
		
		resultMap.put("member", member);
		resultMap.put("message", "success");
    	status = HttpStatus.OK;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
