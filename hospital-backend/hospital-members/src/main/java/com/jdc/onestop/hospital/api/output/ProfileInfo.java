package com.jdc.onestop.hospital.api.output;

import com.jdc.onestop.hospital.domain.member.entity.Account;

public record ProfileInfo(
		String name,
		String role,
		String profile,
		String phone,
		String email) {

	public static ProfileInfo from(Account entity) {
		return new ProfileInfo(entity.getFullName(), entity.getRole().name(), entity.getProfile(), entity.getPhone(), entity.getUsername());
	}
}
