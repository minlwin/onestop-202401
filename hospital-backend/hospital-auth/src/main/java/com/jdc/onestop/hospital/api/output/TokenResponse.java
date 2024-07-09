package com.jdc.onestop.hospital.api.output;

import com.jdc.onestop.hospital.domain.member.entity.Account;

public record TokenResponse(
		String name,
		String role,
		String username,
		String accessToken,
		String refreshToken) {

	public static TokenResponse from(Account account, String accesToken, String refreshToken) {
		return new TokenResponse(account.getFullName(), 
				account.getRole().name(), 
				account.getUsername(), 
				accesToken, 
				refreshToken);
	}

}
