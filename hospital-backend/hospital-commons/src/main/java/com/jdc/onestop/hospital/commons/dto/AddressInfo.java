package com.jdc.onestop.hospital.commons.dto;

import com.jdc.onestop.hospital.domain.utils.embeddables.Address;

public record AddressInfo(
		String building,
		String street,
		String quarter,
		int townshipId,
		String township,
		String district,
		String division) {

	public static AddressInfo from(Address address) {
		
		if(null != address) {
			return new AddressInfo(
				address.getBuilding(), 
				address.getStreet(), 
				address.getQuarter(), 
				address.getTownship().getId(), 
				address.getTownship().getName(), 
				address.getTownship().getDistrict().getName(), 
				address.getTownship().getDistrict().getDivision().getName());
		}
		
		return null;
	}

}
