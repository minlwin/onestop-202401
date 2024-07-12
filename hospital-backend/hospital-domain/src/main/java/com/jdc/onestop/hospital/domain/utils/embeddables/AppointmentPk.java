package com.jdc.onestop.hospital.domain.utils.embeddables;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jdc.onestop.hospital.domain.DomainException;
import com.jdc.onestop.hospital.domain.utils.consts.Section;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AppointmentPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");

	@Column(name = "doctor_id")
	private int doctorId;

	@Column(name = "issue_date")
	private LocalDate issueDate;
	
	private Section section;
	
	@Column(name = "patient_id")
	private int patientId;
	
	public String getCode() {
		return "%s-%s-%03d-%06d".formatted(
				issueDate.format(DF), 
				section, doctorId, patientId);
	}
	
	public static AppointmentPk parse(String code) {
		
		try {
			
			var array = code.split("-");
			var pk = new AppointmentPk();
			pk.setIssueDate(LocalDate.parse(array[0], DF));
			pk.setSection(Section.valueOf(array[1]));
			pk.setDoctorId(Integer.parseInt(array[2]));
			pk.setPatientId(Integer.parseInt(array[3]));
			return pk;
			
		} catch (Exception e) {
			throw new DomainException("Invalid appointment code.", e);
		}
	}
}
