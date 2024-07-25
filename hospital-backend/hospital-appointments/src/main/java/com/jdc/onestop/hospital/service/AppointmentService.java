package com.jdc.onestop.hospital.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.AppointmentEditForm;
import com.jdc.onestop.hospital.api.input.AppointmentSearch;
import com.jdc.onestop.hospital.api.input.AppointmentStatusUpdateForm;
import com.jdc.onestop.hospital.api.output.AppointmentDetails;
import com.jdc.onestop.hospital.api.output.AppointmentListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.member.repo.PatientRepo;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment_;
import com.jdc.onestop.hospital.domain.transaction.repo.AppointmentRepo;
import com.jdc.onestop.hospital.domain.transaction.repo.DoctorScheduleRepo;
import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;
import com.jdc.onestop.hospital.domain.utils.embeddables.AppointmentPk;
import com.jdc.onestop.hospital.domain.utils.embeddables.AppointmentPk_;
import com.jdc.onestop.hospital.domain.utils.embeddables.DoctorSchedulePk;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepo appointmentRepo;
	
	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private DoctorScheduleRepo scheduleRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public AppointmentDetails create(AppointmentEditForm form) {
		
		var limitTime = LocalDateTime.of(form.date(), LocalTime.parse(form.section().getAcceptableTime()));
		if(limitTime.isBefore(LocalDateTime.now())) {
			throw new ApiBusinessException("Appointment can not accept at that time.");
		}
		
		var patient = patientRepo.findOneByAccountUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.orElseThrow(() -> new ApiBusinessException("There is no patient with login id."));
		
		var scheduleId = new DoctorSchedulePk();
		scheduleId.setDoctorId(form.doctorId());
		scheduleId.setIssueDate(form.date());
		scheduleId.setSection(form.section());
		
		var schedule = scheduleRepo.findById(scheduleId)
				.orElseThrow(() -> new ApiBusinessException("There is no schedule for requested information."));
		
		if(schedule.getMaxToken() <= schedule.getCurrentToken()) {
			throw new ApiBusinessException("Appointment can not accept as token is over limit.");
		}
		
		var tokenNumber = schedule.getCurrentToken() + 1;
		schedule.setCurrentToken(tokenNumber);
		
		var appointent = new Appointment();
		appointent.setPatient(patient);
		appointent.setSchedule(schedule);
		appointent.setTokenNumber(tokenNumber);
		appointent.setComplain(form.complain());
		appointent.setStatus(AppointmentStatus.Applied);
		
		appointent = appointmentRepo.saveAndFlush(appointent);
		
		return AppointmentDetails.from(appointent);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public AppointmentDetails update(String id, AppointmentStatusUpdateForm form) {
		
		return appointmentRepo.findById(AppointmentPk.parse(id))
				.stream()
				.peek(form::update)
				.map(AppointmentDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no appointment with given id"));
	}

	@Transactional(readOnly = true)
	public AppointmentDetails findById(String id) {
		return appointmentRepo.findById(AppointmentPk.parse(id))
				.map(AppointmentDetails::from)
				.orElseThrow(() -> new ApiBusinessException("There is no appointment with given id"));
	}

	@Transactional(readOnly = true)
	public PageInfo<AppointmentListItem> search(AppointmentSearch form, int page, int size) {
		return appointmentRepo.search(queryFunc(form), countFunc(form), page, size);
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<AppointmentListItem>> queryFunc(AppointmentSearch form) {
		return cb -> {
			var cq = cb.createQuery(AppointmentListItem.class);
			
			var root = cq.from(Appointment.class);
			AppointmentListItem.select(cq, root);
			
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Appointment_.id).get(AppointmentPk_.issueDate)));
			
			return cq;
		};
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(AppointmentSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			
			var root = cq.from(Appointment.class);
			cq.select(cb.count(root.get(Appointment_.id)));
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}
	

}
