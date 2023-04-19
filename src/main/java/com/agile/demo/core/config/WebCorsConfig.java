package com.agile.demo.core.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment environment;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//front 접근 위해 local 과 vdi만 접근가능하게 mapping
		registry.addMapping("/**").allowedOrigins("*");
	
	}

}
