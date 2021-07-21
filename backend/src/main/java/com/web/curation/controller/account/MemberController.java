package com.web.curation.controller.account;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.user.MemberDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Member;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 201, message = "생성됨", response = BasicResponse.class),
        @ApiResponse(code = 409, message = "중복", response = BasicResponse.class)})
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
    MemberDao memberDao;
	
	@PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public Object signup(@RequestBody Member member) {
		Optional<Member> responseMember = memberDao.getMemberByEmail(member.getEmail());
		ResponseEntity response = null;
		
		System.out.println("확인 수행");
		
		if (!responseMember.isPresent()) {
			memberDao.save(member);
			
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
		
        return response;
    }
}
