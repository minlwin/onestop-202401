package com.jdc.onestop.hospital.utils;

import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

public interface PatientCode {

	String format = "PTE-%04d";
	
	static int parse(String code) {
		
		try {
			var array = code.split("-");
			return Integer.parseInt(array[1]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new ApiBusinessException("Invalid Patient Code");
		}
	}

}
