package com.jdc.onestop.hospital.api.output;

public record TownshipDto(int id, String name,
		int districtId, String district,
		int divisionId, String division) {

}
