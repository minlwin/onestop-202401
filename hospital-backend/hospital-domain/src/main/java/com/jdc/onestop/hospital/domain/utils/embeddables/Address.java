package com.jdc.onestop.hospital.domain.utils.embeddables;

import com.jdc.onestop.hospital.domain.location.entity.Township;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Address {

	private String building;
	private String street;
	private String quarter;
	@ManyToOne
	private Township township;
}
