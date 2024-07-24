package com.jdc.onestop.hospital.domain.member.entity;

import com.jdc.onestop.hospital.domain.AuditableEntity;
import com.jdc.onestop.hospital.domain.utils.consts.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "ACCOUNT")
public class Account extends AuditableEntity{

	@Id
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private MemberRole role;
	
	@Column(nullable = false, name = "full_name")
	private String fullName;

	@Column(nullable = false)
	private String phone;
	
	private String profile;
}
