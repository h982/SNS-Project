package com.web.curation.member;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Service
@AllArgsConstructor
public class NaverLoginBO {
    private final static String CLIENT_ID = "ZjJmqLmuBHPdziT16aMy";
    private final static String CLIENT_SECRET = "Rs8Suuq2FP";
    private final static String REDIRECT_URI = "http://i5c105.p.ssafy.io/naversignup";
    private final static String SESSION_STATE = "oauth_state";
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
    public String getAuthorizationUrl(HttpSession session) {
        String state = generateRandomString();
        session.setAttribute("oauth_state",state);

        OAuth20Service oauthService = new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI)
                .state(state)
                .build(NaverLoginApi.instance());
        return oauthService.getAuthorizationUrl();
    }

    public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
        String sessionState = getSession(session);
        if(StringUtils.pathEquals(sessionState, state)){
            OAuth20Service oauthService = new ServiceBuilder()
                    .apiKey(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .callback(REDIRECT_URI)
                    .state(state)
                    .build(NaverLoginApi.instance());
            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
            System.out.println("accessToken = "+ accessToken);
            return accessToken;
        }
        return null;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    private String getSession(HttpSession session){
        return (String) session.getAttribute("oauth_state");
    }

    public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException{
        OAuth20Service oauthService =new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI).build(NaverLoginApi.instance());
        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
    }
}

