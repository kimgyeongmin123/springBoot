package com.example.demo.config.auth.logoutHandler;


import com.example.demo.config.auth.PrincipalDetails;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuthLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        System.out.println("authentication : "+authentication);
        System.out.println("authentication.getPrincipal() "+authentication.getPrincipal());
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("provider : " + principalDetails.getUser().getProvider());

        String provider = principalDetails.getUser().getProvider();
        String accessToken = ((PrincipalDetails) authentication.getPrincipal()).getAccessToken();

        if(StringUtils.contains(provider,"kakao")){
            System.out.println("GET /th/kakao/logout");

            //URL
            String url = "https://kapi.kakao.com/v1/user/logout";
            //Header
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            headers.add("Authorization", "Bearer "+accessToken);

            //Parameter
            //header + parameter
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

            //Request_Case1
            RestTemplate rt = new RestTemplate();
            ResponseEntity<String> rs =  rt.exchange(url, HttpMethod.POST,entity,String.class);

            System.out.println(rs);
            System.out.println(rs.getBody());
        }

    }
}
