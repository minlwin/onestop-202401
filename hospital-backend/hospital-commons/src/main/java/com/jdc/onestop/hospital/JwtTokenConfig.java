package com.jdc.onestop.hospital;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/jwt-token.properties")
public class JwtTokenConfig {

}
