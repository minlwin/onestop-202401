package com.jdc.onestop.hospital.api.output;

import java.util.List;

import com.jdc.onestop.hospital.domain.member.entity.Account;

public record TokenResponse(
		String name,
		List<String> roles,
		String username,
		String accessToken,
		String refreshToken) {

	public static TokenResponse from(Account account, List<String> roles, String accesToken, String refreshToken) {
		return new TokenResponse(account.getFullName(), 
				roles, 
				account.getUsername(), 
				accesToken, 
				refreshToken);
	}

}
