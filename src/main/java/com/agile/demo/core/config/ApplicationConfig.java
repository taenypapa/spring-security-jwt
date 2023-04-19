package com.agile.demo.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {


	@Bean
	public static PasswordEncoder passwordEncoder() {
		// Spring5부터 PasswordEncoder 지정은 필수로 진행해주어야 합니다. 
		
		return new BCryptPasswordEncoder();
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
	}
}
