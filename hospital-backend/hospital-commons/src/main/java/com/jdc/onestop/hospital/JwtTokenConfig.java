package com.jdc.onestop.hospital;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.jdc.onestop.hospital.security.JwtTokenFilter;
import com.jdc.onestop.hospital.security.JwtTokenGenerator;
import com.jdc.onestop.hospital.security.JwtTokenParser;

@Configuration
@PropertySource(value = "classpath:/jwt-token.properties")
public class JwtTokenConfig {

	@Bean(initMethod = "initBean")
	JwtTokenParser jwtTokenParser() {
		return new JwtTokenParser();
	}
	
	@Configuration
	@Profile("auth-client")
	static class AuthClientConfig {
		
		@Bean
		JwtTokenFilter jwtTokenFilter() {
			return new JwtTokenFilter();
		}
		
	}
	
	@Configuration
	@Profile("auth-server")
	static class AuthServerConfig {
		
		@Bean
		JwtTokenGenerator jwtTokenGenerator() {
			return new JwtTokenGenerator();
		}
	}
}
