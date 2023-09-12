package com.example.demo.config.auth.logoutHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.thymeleaf.util.StringUtils;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	private String kekeoClientId="e7028caff961c903afb20f58c0b76735";
	private String LOGOUT_REDIRECT_URI="http://localhost:8080/login";

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("CustomLogoutSuccessHandler's onLogoutSuccess!");
		response.sendRedirect( request.getContextPath() );

		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		String provider = principalDetails.getUser().getProvider();

		if(StringUtils.contains(provider,"kakao")){

			System.out.println("GET /th/kakao/logoutWithKakao");
			//URL
			String url = "https://kauth.kakao.com/oauth/logout?client_id="+kekeoClientId+"&logout_redirect_uri="+LOGOUT_REDIRECT_URI;
			response.sendRedirect(url);

			return ;
		}

	}

}
