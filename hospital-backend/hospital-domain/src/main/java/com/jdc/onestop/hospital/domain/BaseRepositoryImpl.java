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
		return entityManager.createQuery(queryFunc.apply(entityManager.getCriteriaBuilder()))
				.getResultList();
	}

	@Override
	public <R> PageInfo<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		
		var count = entityManager.createQuery(countFunc.apply(entityManager.getCriteriaBuilder())).getSingleResult();
		
		var contents = entityManager.createQuery(queryFunc.apply(entityManager.getCriteriaBuilder()))
				.setFirstResult(page * size)
				.setMaxResults(size).getResultList();
		
		return new PageInfo<R>(contents, page, size, count);
	}

}
