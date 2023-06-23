package com.jwt;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.jwt.filter.JWTFilter;

@SpringBootApplication
public class UserAuthMsApplication {
	
	
	@Bean
	public FilterRegistrationBean jwtFilter() 
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JWTFilter());
		filterRegistrationBean.addUrlPatterns("/auth/v1/*");
		return filterRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserAuthMsApplication.class, args);
	}

}
