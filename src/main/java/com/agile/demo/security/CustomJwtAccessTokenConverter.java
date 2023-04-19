package com.agile.demo.security;

import com.agile.demo.security.details.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {


		if(authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
			final Map<String, Object> additionalInfo = new HashMap<String, Object>();

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			/** custom value (s) **/
			additionalInfo.put("usrId", userDetails.getUsername());
			additionalInfo.put("name", userDetails.getName());
			/** custom value (e) **/

			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		}
		
		accessToken = super.enhance(accessToken, authentication);
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
		
		return accessToken;
	}

}
