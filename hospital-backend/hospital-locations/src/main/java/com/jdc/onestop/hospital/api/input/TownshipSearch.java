package com.jdc.onestop.hospital.api.input;

public record TownshipSearch(String name, 
		Integer townshipId, String township,
		Integer divisionId, String division) {

}
