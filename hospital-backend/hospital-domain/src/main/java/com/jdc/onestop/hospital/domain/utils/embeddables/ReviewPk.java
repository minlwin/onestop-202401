package com.jdc.onestop.hospital.domain.utils.embeddables;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ReviewPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "doctor_id")
	private int doctorId;

	@Column(name = "patient_id")
	private int patientId;
}
