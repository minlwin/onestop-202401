package com.jdc.onestop.hospital.service;

import java.time.LocalDate;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.commons.dto.DoctorSectionChange;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule;
import com.jdc.onestop.hospital.domain.transaction.repo.DoctorScheduleRepo;
import com.jdc.onestop.hospital.domain.utils.embeddables.DoctorSchedulePk;

@Service
public class DoctorSectionCreationListener {
	
	@Autowired
	private DoctorScheduleRepo scheduleRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@RabbitListener(queues = "#{newSectionQueue.name}")
	public void consume(DoctorSectionChange event) {
		
		var endDate = LocalDate.now().plusDays(7);
		var target = event.changeFrom();
		
		while(target.isBefore(endDate)) {
			for(var section : event.items()) {
				if(target.getDayOfWeek().equals(section.day())) {
					var schedule = new DoctorSchedule();
					var schedulePk = new DoctorSchedulePk();
					
					schedulePk.setDoctorId(event.doctorId());
					schedulePk.setIssueDate(target);
					schedulePk.setSection(section.section());
					schedule.setId(schedulePk);
					
					schedule.setMaxToken(section.maxToken());
					
					scheduleRepo.save(schedule);
				}
			}
			
			target = target.plusDays(1);
		}
	}
}
