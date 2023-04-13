package com.agile.demo.security;

import com.agile.demo.core.properties.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private ApplicationProperties applicationProperties;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("username: {}", username);

        if(applicationProperties.getDefaultId().equals(username)){

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("DEFAULT"));

            CustomUserDetails userDetails = new CustomUserDetails();

            userDetails.setUsername(username);
            userDetails.setPassword(passwordEncoder.encode(applicationProperties.getDefaultPassword()));
            userDetails.setAuthorities(authorities);

            return userDetails;
        } else {
            throw new UsernameNotFoundException("no_users");
        }
    }
}
