package com.jdc.onestop.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.api.input.DivisionSearch;
import com.jdc.onestop.hospital.api.output.DivisionDto;
import com.jdc.onestop.hospital.domain.location.entity.Division;
import com.jdc.onestop.hospital.domain.location.entity.Division_;
import com.jdc.onestop.hospital.domain.location.repo.DivisionRepo;

@Service
public class DivisionService {
	
	@Autowired
	private DivisionRepo repo;

	public List<DivisionDto> search(DivisionSearch form) {
		// select new com.jdc.onestop.hospital.api.output.DivisionDto(d.id, d.name) 
		// from Division d 
		// where lower(d.name) like lower(:name)
		
		return repo.search(cb -> {
			var cq = cb.createQuery(DivisionDto.class);
			
			var root = cq.from(Division.class);
			DivisionDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(Division_.id)));
			
			return cq;
		});
	}

}
