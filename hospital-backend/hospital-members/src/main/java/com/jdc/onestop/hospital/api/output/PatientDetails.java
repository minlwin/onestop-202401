package com.jdc.onestop.hospital.api.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jdc.onestop.hospital.commons.dto.PatientInfo;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment;

public record PatientDetails(		
		PatientInfo patient,
		List<AppointmentSummary> appointments) {

	public static PatientDetails from(Patient patient) {
		return new PatientDetails(PatientInfo.from(patient), summary(patient.getAppointment()));
	}
	
	private static List<AppointmentSummary> summary(List<Appointment> appointments) {
		
		var map = new HashMap<Doctor, List<Appointment>>();
		
		for(var appointment : appointments) {
			var appointmentsForDoctor = map.get(appointment.getSchedule().getDoctor());
			
			if(null == appointmentsForDoctor) {
				appointmentsForDoctor = new ArrayList<>();
				map.put(appointment.getSchedule().getDoctor(), appointmentsForDoctor);
			}
			
			appointmentsForDoctor.add(appointment);
		}

		var list = new ArrayList<AppointmentSummary>();
		
		for(var doctor : map.keySet()) {
			var appointmentsForDoctor = map.get(doctor);
			
			var applied = 0;
			var cancled = 0;
			var finished = 0;
			
			for(var item : appointmentsForDoctor) {
				switch(item.getStatus()) {
				case Applied -> applied ++;
				case Canceled -> cancled ++;
				case Finished -> finished ++;
				}
			}
			
			list.add(new AppointmentSummary(
					doctor.getCode(), 
					doctor.getAccount().getFullName(), 
					doctor.getDegree(), 
					applied, 
					finished, 
					cancled));
		}
		
		return list;
	}

}
