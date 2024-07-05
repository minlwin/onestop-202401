package com.jdc.onestop.hospital.domain.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.AuditableEntity;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;
import com.jdc.onestop.hospital.domain.utils.embeddables.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "PATIENT")
@EqualsAndHashCode(callSuper = false)
public class Patient extends AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToOne(optional = false)
	private Account account;
	
	private Gender gender;
	private LocalDate dob;
	
	@Column(name = "register_at")
	private LocalDateTime registerAt;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String email;

	private Address address;

}
