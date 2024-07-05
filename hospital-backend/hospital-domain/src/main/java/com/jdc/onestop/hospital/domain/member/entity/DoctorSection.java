package com.jdc.onestop.hospital.domain.member.entity;

import java.time.DayOfWeek;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DOCTOR_SECTION")
public class DoctorSection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional = false)
	private Doctor doctor;
	
	@Column(nullable = false)
	private DayOfWeek day;
	
	@Column(nullable = false)
	private Section section;
	
	private Integer maxToken;
}
