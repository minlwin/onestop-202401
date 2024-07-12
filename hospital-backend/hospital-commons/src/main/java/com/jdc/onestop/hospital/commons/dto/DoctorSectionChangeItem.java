package com.jdc.onestop.hospital.commons.dto;

import java.time.DayOfWeek;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

public record DoctorSectionChangeItem(
		DayOfWeek day, Section section, Integer maxToken) {

}
