package com.web.curation.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.web.curation.member.JwtServiceImpl;
import com.web.curation.member.Member;
import com.web.curation.member.challenge.ChallengeService;

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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
	// test1
    @Autowired
    MemberService memberService;
    
    @Autowired
    ChallengeService challengeService;

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
            MemberDto responseMemberDto = memberService.registMember(memberDto);
            challengeService.createChallenge(responseMemberDto.getMemberId());
            
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
            	challengeService.attend(loginUser.get().getMemberId());
            	
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
    
    @ApiOperation(value = "회원정보 수정")
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateInfo(@RequestBody MemberDto memberDto){
        Map<String, Object> resultMap = new HashMap<>();
        Optional<MemberDto> responseMemberDto = memberService.updateMember(memberDto);
        
        if(responseMemberDto.isPresent()) {
        	Map<String, Object> dataMap = new HashMap<>();
        	dataMap.put("memberInfo", responseMemberDto);
        	resultMap.put("data", dataMap);
            resultMap.put("message", "success");
            
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }

        resultMap.put("message", "해당하는 email이 없습니다.");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NOT_FOUND);
    }
    
    @ApiOperation(value = "카카오 로그인")
    @GetMapping("/kakao")
    public ResponseEntity<Map<String, Object>> kakaoLogin(@RequestParam String code){
        Map<String, Object> resultMap = new HashMap<>();
        
        String token = getKakaoToken(code);
        Map<String, String> userInfo = getKaKaoUserInfo(token);
        Optional<MemberDto> dto = memberService.getMemberByEmail(userInfo.get("email"));
        
        resultMap.put("message", "카카오 유저 정보");
        resultMap.put("data", userInfo);
        resultMap.put("member", dto);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

	private String getKakaoToken(String code) {
		String access_Token = "";
		String refresh_Token = "";
		HttpURLConnection conn = null;
		
		try {
			URL url = new URL("https://kauth.kakao.com/oauth/token");
			conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=4ae2f007d84e033a263932b5176355f1");
			sb.append("&redirect_uri=http://localhost:8081/kakaosignup");
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return access_Token;
	}
	
	public Map<String, String> getKaKaoUserInfo(String access_Token) {

        Map<String, String> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            
            String id = element.getAsJsonObject().get("id").getAsString();
            String email = null;
            if (kakao_account.getAsJsonObject().get("email") != null) {
                email = kakao_account.getAsJsonObject().get("email").getAsString();
                userInfo.put("id", id);
                userInfo.put("email", email);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
