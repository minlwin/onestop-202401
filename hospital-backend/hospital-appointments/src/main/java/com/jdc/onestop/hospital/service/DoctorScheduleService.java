package com.jdc.onestop.hospital.service;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.ScheduleSearch;
import com.jdc.onestop.hospital.api.output.ScheduleListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.member.repo.DoctorRepo;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule_;
import com.jdc.onestop.hospital.domain.transaction.repo.DoctorScheduleRepo;
import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;
import com.jdc.onestop.hospital.domain.utils.embeddables.DoctorSchedulePk;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class DoctorScheduleService {
	
	@Autowired
	private DoctorScheduleRepo scheduleRepo;
	@Autowired
	private DoctorRepo doctorRepo;

	@Transactional(readOnly = true)
	public PageInfo<ScheduleListItem> search(ScheduleSearch form, int page, int size) {
		return scheduleRepo.search(queryFunc(form), countFunc(form), page, size);
	}
	
	@Scheduled(cron = "0 30 0 * * ?")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void createSchedules() {
		var doctors = doctorRepo.findByStatus(DoctorStatus.OnDuty);
		
		for(var doctor : doctors) {
			
			var start = LocalDate.now();
			var end = start.plusWeeks(1);
			
			while(start.isBefore(end)) {
				
				for(var section : doctor.getSection()) {
					if(start.getDayOfWeek() == section.getDay()) {
						var schedulePk = new DoctorSchedulePk();
						schedulePk.setDoctorId(doctor.getId());
						schedulePk.setIssueDate(start);
						schedulePk.setSection(section.getSection());
						
						if(scheduleRepo.findById(schedulePk).isEmpty()) {
							var schedule = new DoctorSchedule();
							schedule.setId(schedulePk);
							schedule.setMaxToken(section.getMaxToken());
							scheduleRepo.save(schedule);
						}
					}
				}
				
				start = start.plusDays(1);
			}
		}
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<ScheduleListItem>> queryFunc(ScheduleSearch form) {
		return cb -> {
			var cq = cb.createQuery(ScheduleListItem.class);
			var root = cq.from(DoctorSchedule.class);
			
			ScheduleListItem.select(cq, root);
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(ScheduleSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(DoctorSchedule.class);
			
			cq.select(cb.count(root.get(DoctorSchedule_.id)));
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}
}
