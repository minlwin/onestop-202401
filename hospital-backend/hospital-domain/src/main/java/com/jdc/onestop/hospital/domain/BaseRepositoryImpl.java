package com.jdc.onestop.hospital.domain;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{

	private EntityManager entityManager;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
		var query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public <R> PageInfo<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		
		var countCriteriaQuery = countFunc.apply(entityManager.getCriteriaBuilder());
		var countQuery = entityManager.createQuery(countCriteriaQuery);
		var count = countQuery.getSingleResult();
		
		var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
		var query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		var contents = query.getResultList();
		
		return PageInfo.<R>builder()
		 	.contents(contents)
		 	.page(page)
		 	.size(size)
		 	.count(count)
		 	.build();
	}

}
