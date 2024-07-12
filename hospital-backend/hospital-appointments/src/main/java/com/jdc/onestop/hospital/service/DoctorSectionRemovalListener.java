package com.jdc.onestop.hospital.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.commons.dto.DoctorSectionChange;
import com.jdc.onestop.hospital.domain.transaction.repo.DoctorScheduleRepo;
import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;

@Service
public class DoctorSectionRemovalListener {
	
	@Autowired
	private DoctorScheduleRepo scheduleRepo;

	@RabbitListener(queues = "#{deleteSectionQueue.name}")
	public void consume(DoctorSectionChange event) {
		
		var schedules = scheduleRepo.searchForUpdate(event.doctorId(), event.changeFrom());
		
		for(var schedule : schedules) {
			schedule.setDeleted(true);
			
			for(var appointment : schedule.getAppointment()) {
				appointment.setStatus(AppointmentStatus.Canceled);
				appointment.setChangeBy("System");
				appointment.setChangeReason("Doctor has changed schedules.");
			}
		}
	}

}
