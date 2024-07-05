package com.jdc.onestop.hospital.domain.utils.consts;

public enum DoctorStatus implements EmployeeStatus {

	OnDuty {
		@Override
		public String getDisplayName() {
			return "On Duty";
		}
	}, Retired;
	
	public String getDisplayName() {
		return name();
	}

	@Override
	public String getCode() {
		return name();
	}
}
