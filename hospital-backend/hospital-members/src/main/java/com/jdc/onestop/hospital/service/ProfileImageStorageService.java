package com.jdc.onestop.hospital.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

@Service
public class ProfileImageStorageService {

	@Value("${app.member.profile-path}")
	private String storageFolder;
	
	@Value("${app.member.file-extensions}")
	private String[] extensions;
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
	
	
	public String saveProfile(int id, MultipartFile file) {
		
		validate(file);
		
		try {
			var fileName = getFileName(id, file);
			var filePath = Path.of(storageFolder, fileName);
			
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException e) {
			throw new ApiBusinessException("Image can't save in the storage.", e);
		}
		
	}

	private void validate(MultipartFile file) {
		var extension = getExtension(file.getOriginalFilename());
		
		if(!List.of(extensions).contains(extension.toLowerCase())) {
			throw new ApiBusinessException("Invalid image file extension.");
		}
	}

	private String getExtension(String originalFilename) {
		var array = originalFilename.split("\\.");
		
		if(array.length == 0) {
			throw new ApiBusinessException("Invalid image file extension.");
		}
		
		return array[array.length - 1];
	}

	private String getFileName(int id, MultipartFile file) {
		var extension = getExtension(file.getOriginalFilename());
		return "profile-%04d.%s.%s".formatted(id, LocalDateTime.now().format(DF) ,extension);
	}
}
