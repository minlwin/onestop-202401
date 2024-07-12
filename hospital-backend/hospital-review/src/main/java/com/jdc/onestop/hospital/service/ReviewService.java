package com.jdc.onestop.hospital.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.ReviewEditForm;
import com.jdc.onestop.hospital.api.input.ReviewSearch;
import com.jdc.onestop.hospital.api.output.ReviewDetails;
import com.jdc.onestop.hospital.api.output.ReviewListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.member.repo.DoctorRepo;
import com.jdc.onestop.hospital.domain.member.repo.PatientRepo;
import com.jdc.onestop.hospital.domain.transaction.entity.Review;
import com.jdc.onestop.hospital.domain.transaction.entity.Review_;
import com.jdc.onestop.hospital.domain.transaction.repo.ReviewRepo;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepo reviewRepo;
	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private PatientRepo patientRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public ReviewDetails create(ReviewEditForm form) {
		
		var patient = patientRepo.findOneByAccountUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.orElseThrow(() -> new ApiBusinessException("Invalid patient login id."));
		
		var doctor = doctorRepo.findById(form.doctorId())
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with givin doctor id."));
		
		var review = new Review();
		review.setDoctor(doctor);
		review.setPatient(patient);
		
		review.setStar(form.stars());
		review.setReason(form.reason());
		
		review = reviewRepo.saveAndFlush(review);
		
		return ReviewDetails.from(review);
	}

	@Transactional(readOnly = true)
	public PageInfo<ReviewListItem> search(ReviewSearch form, int page, int size) {
		return reviewRepo.search(queryFunc(form), countFunc(form), page, size);
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<ReviewListItem>> queryFunc(ReviewSearch form) {
		return cb -> {
			var cq = cb.createQuery(ReviewListItem.class);
			var root = cq.from(Review.class);
			
			ReviewListItem.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Review_.createAt)));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(ReviewSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Review.class);
			cq.select(cb.count(root.get(Review_.id)));
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}
}
