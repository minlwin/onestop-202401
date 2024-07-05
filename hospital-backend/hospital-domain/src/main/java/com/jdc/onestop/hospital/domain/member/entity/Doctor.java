package com.jdc.onestop.hospital.domain.member.entity;

import java.util.List;

import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "OFFICE_STAFF")
public class Doctor extends Employee {

	private String profile;
	
	@Column(nullable = false)
	private String degree;
	
	@Column(nullable = false)
	private DoctorStatus status;
	
	@OneToMany(mappedBy = "doctor")
	private List<DoctorSection> section;
}
