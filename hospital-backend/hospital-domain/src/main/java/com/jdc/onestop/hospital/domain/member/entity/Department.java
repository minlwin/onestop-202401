package com.jdc.onestop.hospital.domain.member.entity;

import java.util.ArrayList;
import java.util.List;

import com.jdc.onestop.hospital.domain.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "DEPARTMENT")
@EqualsAndHashCode(callSuper = false)
public class Department extends AuditableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	private Employee head;
	
	private String phone;
	private String email;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> employee = new ArrayList<>(); 
}
