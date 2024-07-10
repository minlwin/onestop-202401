package com.jdc.onestop.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.api.input.TownshipSearch;
import com.jdc.onestop.hospital.api.output.TownshipDto;
import com.jdc.onestop.hospital.domain.location.entity.Township;
import com.jdc.onestop.hospital.domain.location.entity.Township_;
import com.jdc.onestop.hospital.domain.location.repo.TownshipRepo;

@Service
public class TownshipService {
	
	@Autowired
	private TownshipRepo repo;

	public List<TownshipDto> search(TownshipSearch form) {
		return repo.search(cb -> {
			var cq = cb.createQuery(TownshipDto.class);
			
			var root = cq.from(Township.class);
			TownshipDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(Township_.id)));
			
			return cq;
		});
	}

}
