package com.jdc.onestop.hospital.domain.transaction.entity;

import com.jdc.onestop.hospital.domain.AuditableEntity;
import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;
import com.jdc.onestop.hospital.domain.utils.embeddables.AppointmentPk;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "APPOINTMENT")
@EqualsAndHashCode(callSuper = false)
public class Appointment extends AuditableEntity {

	@EmbeddedId
	private AppointmentPk id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
	@JoinColumn(name = "issue_date", referencedColumnName = "issue_date", insertable = false, updatable = false)
	@JoinColumn(name = "section", referencedColumnName = "section", insertable = false, updatable = false)
	private DoctorSchedule schedule;
	
	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Patient patient;
	
	@Column(name = "token_number")
	private int tokenNumber;
	private String complain;
	
	private AppointmentStatus status;
	
	@Column(name = "change_reason")
	private String changeReason;

	@Column(name = "change_by")
	private String changeBy;
}
