package com.jdc.onestop.hospital.domain.member.entity;

import java.util.ArrayList;
import java.util.List;

import com.jdc.onestop.hospital.domain.transaction.entity.Review;
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
@Table(name = "DOCTOR")
public class Doctor extends Employee {

	@Column(nullable = false)
	private String degree;
	
	@Column(nullable = false)
	private DoctorStatus status;
	
	@OneToMany(mappedBy = "doctor")
	private List<DoctorSection> section = new ArrayList<>();
	
	@OneToMany(mappedBy = "doctor")
	private List<Review> review = new ArrayList<>();
	
	private int star;
}
