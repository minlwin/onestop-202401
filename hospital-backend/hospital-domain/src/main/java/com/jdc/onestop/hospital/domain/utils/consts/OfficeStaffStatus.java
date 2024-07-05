package com.jdc.onestop.hospital.domain.utils.consts;

public enum OfficeStaffStatus implements EmployeeStatus{

	Assigned, Retired;

	@Override
	public String getCode() {
		return name();
	}

	@Override
	public String getDisplayName() {
		return name();
	}
}
