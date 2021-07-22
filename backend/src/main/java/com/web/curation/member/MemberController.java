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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.web.curation.model.BasicResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static java.time.LocalDateTime.now;

@ApiResponses(value = {@ApiResponse(code = 201, message = "생성됨", response = BasicResponse.class),
        @ApiResponse(code = 409, message = "중복", response = BasicResponse.class)})
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberDao memberDao;

    EntityManager em;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Member member, Model model, HttpSession session) {
        Optional<Member> responseMember = memberDao.getMemberByEmail(member.getEmail());
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        System.out.println(member.getEmail());
        System.out.println(member.getName());
        System.out.println(member.getPhone());
        System.out.println(member.getPassword());
        System.out.println(member.getAddress());
        System.out.println(member.getAddressDetail());
        System.out.println(member.getZonecode());
        System.out.println(member.getSex());
        System.out.println(member.getPoint());
        System.out.println(member.getMbti());
        System.out.println(member.getCreateDate());
        System.out.println(member.getAuthentication());
        System.out.println("확인 수행");

        if (!responseMember.isPresent()) {
            memberDao.save(member);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
            System.out.println(resultMap.get("message"));
        } else {
            resultMap.put("message", "fail");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        System.out.println(resultMap.get("message"));
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
