package com.agile.demo.core.config;

import com.agile.demo.core.properties.ApplicationProperties;
import com.agile.demo.security.CustomJwtAccessTokenConverter;
import com.agile.demo.security.details.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Value("${security.oauth2.resource.jwt.key-value}")
	private String jwtKeyValue;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// 인증 과정 endpoint에 대한 설정을 해줍니다.
		super.configure(endpoints);

		endpoints.accessTokenConverter(jwtAccessTokenConverter())
				.authenticationManager(authenticationManager)
				.userDetailsService(customUserDetailsService)
				.tokenStore(tokenStore())
				.reuseRefreshTokens(false);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// oauth_client_details 테이블에 등록된 사용자로 조회합니다.
		clients.inMemory()
				.withClient(applicationProperties.getClientId()) // 클라이언트 아이디
				.secret(passwordEncoder.encode(applicationProperties.getClientSecret())) // 클라이언트 시크릿
				.authorizedGrantTypes("password","client_credentials","refresh_token")
				.scopes("read", "write")    // 해당 클라이언트의 접근 범위
				.accessTokenValiditySeconds(60 * 10 )            // access token 유효 기간 (10분)
				.refreshTokenValiditySeconds(60 * 60 * 24 * 7);   // refresh token 유효 기간 (7일)

	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		CustomJwtAccessTokenConverter accessTokenConverter = new CustomJwtAccessTokenConverter();
		accessTokenConverter.setSigningKey(jwtKeyValue);

		return accessTokenConverter;
	}

}
