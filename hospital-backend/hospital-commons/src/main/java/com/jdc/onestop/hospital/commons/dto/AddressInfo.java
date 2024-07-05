package com.jdc.onestop.hospital.commons.dto;

public record AddressInfo(
		String building,
		String street,
		String quarter,
		int townshipId,
		String township,
		String district,
		String division) {

}
