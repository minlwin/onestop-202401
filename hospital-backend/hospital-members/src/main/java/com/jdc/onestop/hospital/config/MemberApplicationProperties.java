package com.jdc.onestop.hospital.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.member")
public class MemberApplicationProperties {

	private String profilePath;
	
	private String[] imageFileExtensions;
}
