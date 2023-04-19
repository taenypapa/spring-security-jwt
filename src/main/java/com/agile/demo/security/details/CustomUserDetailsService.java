package com.agile.demo.security.details;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.account.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<AccountEntity> optionalAccount = accountRepository.findByUserId(username);//loginUserId or employeeNo

		if(optionalAccount.isPresent()) {
			AccountEntity account = optionalAccount.get();

			return makeLoginUser(account);
		} else {
			UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("user_name_not_found");
			throw usernameNotFoundException;
		}
	}
	
	// UserInformation 값 주입해 줍니다.
	public CustomUserDetails makeLoginUser(AccountEntity account) {

		CustomUserDetails loginUser  = new CustomUserDetails();
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(account.getRole()));

		loginUser.setUsername(account.getUserId());
		loginUser.setName(account.getName());
		loginUser.setPassword(StringUtils.lowerCase(account.getPassword()));
		loginUser.setAuthorities(authorities);
		
		return loginUser;
	}
}
