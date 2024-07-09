package com.jdc.onestop.hospital.domain.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.AuditableEntity;
import com.jdc.onestop.hospital.domain.utils.embeddables.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "EMPLOYEE")
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee extends AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional = false)
	private Department department;
	
	@Column(nullable = false)
	private LocalDate assignAt;

	@Column(nullable = false)
	private String phone;
	
	private String email;
	
	@ManyToOne(optional = false)
	private Account account;
	
	private Address address;
	
	@Column(name = "change_at")
	private LocalDateTime changeAt;

	@Column(name = "change_reason")
	private String changeReason;
	
	private boolean activated;
	private LocalDateTime activatedAt;

}
