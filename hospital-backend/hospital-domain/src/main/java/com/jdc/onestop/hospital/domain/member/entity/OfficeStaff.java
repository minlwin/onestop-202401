package com.jdc.onestop.hospital.domain.member.entity;

import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "OFFICE_STAFF")
public class OfficeStaff extends Employee {

	@Column(nullable = false)
	private String position;

	@Column(nullable = false)
	private OfficeStaffStatus status;
}
