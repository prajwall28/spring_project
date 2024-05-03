package com.xworkz.vendormanagement.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.OrderEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {
	@Autowired
	private EntityManagerFactory emf;
	@Override
	public boolean save(OrderEntity orderEntity) {
		log.info("Invoking save...");
		log.info("OrderEntity Passed:" + orderEntity);
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			log.info("Et begin");
			em.persist(orderEntity);
			log.info("Persist complete");
			et.commit();

		} catch (PersistenceException pe) {
			log.info("PersistenceException in save:" + pe.getMessage());
			et.rollback();
		} finally {
			log.info("Closing resources");
			em.close();
		}

		return true;
	}
}
