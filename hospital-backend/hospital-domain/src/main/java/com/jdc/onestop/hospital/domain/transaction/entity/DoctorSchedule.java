package com.jdc.onestop.hospital.domain.transaction.entity;

import java.util.ArrayList;
import java.util.List;

import com.jdc.onestop.hospital.domain.AuditableEntity;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.utils.embeddables.DoctorSchedulePk;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "DOCTOR_SCHEDULE")
@EqualsAndHashCode(callSuper = false)
public class DoctorSchedule extends AuditableEntity{

	@EmbeddedId
	private DoctorSchedulePk id;
	
	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Doctor doctor;
	
	@Column(name = "current_token")
	private int currentToken;
	
	@Column(name = "max_token")
	private Integer maxToken;
	
	private boolean deleted;
	
	@OneToMany(mappedBy = "schedule")
	private List<Appointment> appointment = new ArrayList<>(); 
	
}
