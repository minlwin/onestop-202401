package com.jdc.onestop.hospital.api.input;

import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;

public record OfficeStaffSearch(
		OfficeStaffStatus status,
		String keyword) {

}
