package com.example.demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CustomHandler implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[HANDLER MAPPING] CustomHandler's handleRequest ! ");
		return null;
	}
	
	public void WOW() {
		System.out.println("[HANDLER MAPPING] CustomHandler's WOW !");
	}

}
