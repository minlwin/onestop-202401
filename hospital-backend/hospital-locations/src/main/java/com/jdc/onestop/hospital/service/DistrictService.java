package com.jdc.onestop.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.api.input.DistrictSearch;
import com.jdc.onestop.hospital.api.output.DistrictDto;
import com.jdc.onestop.hospital.domain.location.entity.District;
import com.jdc.onestop.hospital.domain.location.entity.District_;
import com.jdc.onestop.hospital.domain.location.repo.DistrictRepo;

@Service
public class DistrictService {
	
	@Autowired
	private DistrictRepo repo;

	public List<DistrictDto> search(DistrictSearch form) {
		return repo.search(cb -> {
			var cq = cb.createQuery(DistrictDto.class);
			
			var root = cq.from(District.class);
			DistrictDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(District_.id)));
			
			return cq;
		});
	}

}
