package com.jdc.onestop.hospital.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.member")
public class MemberApplicationProperties {

	private String profilePath;
	
	private String imageFileExtensions;
}
