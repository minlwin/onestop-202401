package com.jdc.onestop.hospital.commons.dto;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.utils.consts.Gender;

public record PatientInfo(
		int id,
		String code,
		String name,
		Gender gender,
		LocalDateTime registerAt,
		String phone,
		String email,
		AddressInfo address) {

}
