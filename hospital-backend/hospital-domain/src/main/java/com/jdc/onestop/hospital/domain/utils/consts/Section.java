package com.jdc.onestop.hospital.domain.utils.consts;

public enum Section {
	Morning("10:00"), Evening("20:00");
	
	private String acceptableTime;

	private Section(String acceptableTime) {
		this.acceptableTime = acceptableTime;
	}
	
	public String getAcceptableTime() {
		return acceptableTime;
	}
}
