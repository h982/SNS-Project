package com.web.curation.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.web.curation.model.BasicResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static java.time.LocalDateTime.now;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberservice;
    
    EntityManager em;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "회원이 생성됨", response = BasicResponse.class),
    						@ApiResponse(code = 409, message = "중복된 값이 있음", response = BasicResponse.class)})
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Member member) {
        Optional<Member> responseMember = memberservice.checkEmail(member.getEmail());
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        member.setCreateDate(now());
        if (!responseMember.isPresent()) {
            memberservice.registMember(member);
            resultMap.put("message", "success");
            status = HttpStatus.CREATED;
            System.out.println(resultMap.get("message"));
        } else {
            resultMap.put("message", "fail");
            status = HttpStatus.CONFLICT;
        }
        System.out.println(resultMap.get("message"));
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "회원을 반환", response = Member.class),
    						@ApiResponse(code = 404, message = "해당 회원을 찾지 못함")})
    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password) {
    	Map<String, Object> resultMap = new HashMap<>();
    	HttpStatus status = null;
        System.out.println("확인");
    	Optional<Member> responseMember = memberservice.getUser(email, password);
    	if(responseMember.isPresent()) {
    		resultMap.put("message", "success");
        	status = HttpStatus.OK;
        	
        	resultMap.put("member", responseMember);
    	}
    	else {
    		resultMap.put("message", "해당 회원이 존재하지 않습니다.");
        	status = HttpStatus.NOT_FOUND;
    	}
    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
