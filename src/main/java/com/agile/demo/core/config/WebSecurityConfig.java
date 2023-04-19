package com.agile.demo.core.config;

import com.agile.demo.security.CustomAuthenticationProvider;
import com.agile.demo.security.details.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomUserDetailsService authUserDetailsService;
	private PasswordEncoder passwordEncoder;
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		// custom user 인증 서비스를 사용하기위한 설정입니다.
		builder.authenticationProvider(customAuthenticationProvider)
				.userDetailsService(authUserDetailsService)
		;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// authenticationManage 빈 등록
		return super.authenticationManagerBean();
	}
}
