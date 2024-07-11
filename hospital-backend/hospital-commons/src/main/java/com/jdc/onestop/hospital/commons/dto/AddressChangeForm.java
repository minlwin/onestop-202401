package com.jdc.onestop.hospital.commons.dto;

import com.jdc.onestop.hospital.domain.location.entity.Township;
import com.jdc.onestop.hospital.domain.utils.embeddables.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressChangeForm(
		@NotBlank(message = "Please enter building address.")
		String building,
		@NotBlank(message = "Please enter street address.")
		String street,
		@NotBlank(message = "Please enter quarter address.")
		String quarter,
		@NotNull(message = "Please select township.")
		Integer townshipId) {

	public Address address(Township township) {
		var address = new Address();
		address.setBuilding(building);
		address.setStreet(street);
		address.setQuarter(quarter);
		address.setTownship(township);
		return address;
	}

}
