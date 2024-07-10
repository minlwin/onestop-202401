package com.jdc.onestop.hospital.api.output.dto;

import java.time.DayOfWeek;

import com.jdc.onestop.hospital.domain.member.entity.DoctorSection;
import com.jdc.onestop.hospital.domain.utils.consts.Section;

public record DoctorSectionInfo(
		DayOfWeek day,
		Section section,
		int maxToken) implements Comparable<DoctorSectionInfo>{

	public static DoctorSectionInfo from(DoctorSection entity) {
		return new DoctorSectionInfo(
				entity.getDay(), 
				entity.getSection(), 
				entity.getMaxToken());
	}

	@Override
	public int compareTo(DoctorSectionInfo o) {
		
		var result = day.compareTo(o.day());
		
		if(result == 0) {
			return section.compareTo(o.section);
		}
		
		return result;
	}
}
