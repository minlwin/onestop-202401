package com.jdc.onestop.hospital.domain.location.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DIVISION")
public class Division {

	@Id
	private int id;
	
	@Column(nullable = false)
	private String name;
}
