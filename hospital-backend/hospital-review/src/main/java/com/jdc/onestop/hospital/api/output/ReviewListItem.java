package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Department_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.entity.Patient_;
import com.jdc.onestop.hospital.domain.transaction.entity.Review;
import com.jdc.onestop.hospital.domain.transaction.entity.Review_;
import com.jdc.onestop.hospital.domain.utils.embeddables.ReviewPk_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record ReviewListItem(
		int doctorId,
		String doctorName,
		String degree,
		String department,
		int patientId,
		String patientName,
		int stars,
		String reason,
		LocalDateTime reviewAt) {

	public static void select(CriteriaQuery<ReviewListItem> cq, Root<Review> root) {
		cq.multiselect(
			root.get(Review_.id).get(ReviewPk_.doctorId),
			root.get(Review_.doctor).get(Doctor_.account).get(Account_.fullName),
			root.get(Review_.doctor).get(Doctor_.degree),
			root.get(Review_.doctor).get(Doctor_.department).get(Department_.name),
			root.get(Review_.id).get(ReviewPk_.patientId),
			root.get(Review_.patient).get(Patient_.account).get(Account_.fullName),
			root.get(Review_.star),
			root.get(Review_.reason),
			root.get(Review_.createAt)		
		);
	}
}
