package com.agile.demo.security;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.account.AccountRepository;
import com.agile.demo.security.details.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        Optional<AccountEntity> optional = accountRepository.findByUserId(authentication.getName());

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("username_is_not_found-" + username);
        }

        if (optional.isPresent() && passwordEncoder.matches(password, optional.get().getPassword())) {
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

            authenticationToken.setDetails(user);
            return authenticationToken;
        } else {
            throw new BadCredentialsException("password_is_not_matched");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        /** 다른 조건이 없으면 무조건 통과 */
        return true;
    }
}
