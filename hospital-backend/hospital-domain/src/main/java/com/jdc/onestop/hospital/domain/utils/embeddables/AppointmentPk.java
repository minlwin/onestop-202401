package com.jdc.onestop.hospital.domain.utils.embeddables;

import java.io.Serializable;
import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AppointmentPk implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "doctor_id")
	private int doctorId;

	@Column(name = "issue_date")
	private LocalDate issueDate;
	
	private Section section;
	
	@Column(name = "patient_id")
	private int patientId;
}
