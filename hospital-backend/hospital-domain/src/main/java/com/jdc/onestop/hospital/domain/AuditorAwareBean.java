package com.jdc.onestop.hospital.domain;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareBean implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(ctx -> ctx.getAuthentication())
				.map(auth -> auth.getName())
				.or(() -> Optional.of("System User"));
	}

}
