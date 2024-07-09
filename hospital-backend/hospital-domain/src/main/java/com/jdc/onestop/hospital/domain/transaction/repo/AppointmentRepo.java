package com.jdc.onestop.hospital.domain.transaction.repo;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment;
import com.jdc.onestop.hospital.domain.utils.embeddables.AppointmentPk;

public interface AppointmentRepo extends BaseRepository<Appointment, AppointmentPk>{

}
