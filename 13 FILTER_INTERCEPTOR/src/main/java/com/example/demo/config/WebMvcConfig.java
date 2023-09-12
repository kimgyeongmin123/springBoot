package com.example.demo.config;

import com.example.demo.interceptor.MemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer{

	

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}



	//INTERCEPTOR
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MemoInterceptor())
				.addPathPatterns("/memo/**")
				.excludePathPatterns("/resources/**");
	}
}
