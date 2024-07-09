package com.jdc.onestop.hospital.domain.transaction.entity;

import com.jdc.onestop.hospital.domain.AuditableEntity;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.utils.embeddables.ReviewPk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "REVIEW")
@EqualsAndHashCode(callSuper = false)
public class Review extends AuditableEntity{

	@EmbeddedId
	private ReviewPk id;
	
	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Doctor doctor;

	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Patient patient;
	
	private int star;
	private String reason;
}
