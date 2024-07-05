package com.jdc.onestop.hospital.api.output;

public record TokenResponse(
		String name,
		String role,
		String username,
		String accessToken,
		String refreshToken) {

}
