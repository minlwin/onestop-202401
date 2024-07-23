package com.jdc.onestop.hospital.domain.transaction.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule;
import com.jdc.onestop.hospital.domain.utils.embeddables.DoctorSchedulePk;

public interface DoctorScheduleRepo extends BaseRepository<DoctorSchedule, DoctorSchedulePk>{

	@Query("select s from DoctorSchedule s where s.id.doctorId = ?1 and s.id.issueDate >= ?2")
	List<DoctorSchedule> searchForUpdate(int doctorId, LocalDate dateForm);

}
