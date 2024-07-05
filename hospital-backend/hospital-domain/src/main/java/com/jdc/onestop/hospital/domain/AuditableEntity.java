package com.jdc.onestop.hospital.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AuditableEntity {

	@CreatedBy
	@Column(name = "create_by")
	private String createBy;
	
	@CreatedDate
	@Column(name = "create_at")
	private LocalDateTime createAt;
	
	@LastModifiedBy
	@Column(name = "update_by")
	private String updateBy;

	@LastModifiedDate
	@Column(name = "update_at")
	private LocalDateTime updateAt;
}
