package com.jdc.onestop.hospital.domain.location.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TOWNSHIP")
public class Township {

	@Id
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(optional = false)
	private District tiDistrict;
}