package com.web.curation.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.web.curation.member.JwtServiceImpl;
import com.web.curation.member.Member;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
	// test1
    @Autowired
    MemberService memberService;

    @Autowired
    private JwtServiceImpl jwtService;

    EntityManager em;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "회원이 생성됨", response = BasicResponse.class),
    						@ApiResponse(code = 409, message = "중복된 값이 있음", response = BasicResponse.class)})
    public ResponseEntity<Map<String, Object>> signup(@RequestBody MemberDto memberDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        memberDto.setAuthenticated(false);
        if (!memberService.hasSameEmail(memberDto.getEmail())) {
            memberService.registMember(memberDto);
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

    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            Optional<MemberDto> loginUser = memberService.getUser(memberDto.getEmail(),memberDto.getPassword());
            if (loginUser.isPresent()) {
                String token = jwtService.create("memberEmail", loginUser.get().getEmail(), "access-token");// key, data, subject
                resultMap.put("access-token", token);
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", "fail");
                status = HttpStatus.CONFLICT;
            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
    @GetMapping("/info/{memberEmail}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("memberEmail") @ApiParam(value = "인증할 회원의 아이디.", required = true) String memberEmail,
            HttpServletRequest request) {
        System.out.println("회원인증");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        if (jwtService.isUsable(request.getHeader("access-token"))) {
            try {
                Optional<MemberDto> member = memberService.getMemberByEmail(memberEmail);
                System.out.println(member);
                resultMap.put("memberInfo", member);
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            resultMap.put("message", "fail");
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
