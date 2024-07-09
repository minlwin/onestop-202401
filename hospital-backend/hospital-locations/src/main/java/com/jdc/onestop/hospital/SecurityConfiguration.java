package com.jdc.onestop.hospital;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import com.jdc.onestop.hospital.security.JwtTokenFilter;

@Configuration
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> {});
		
		http.authorizeHttpRequests(req -> {
			req.anyRequest().authenticated();
		});
		
		http.addFilterAfter(jwtTokenFilter, ExceptionTranslationFilter.class);
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
}
