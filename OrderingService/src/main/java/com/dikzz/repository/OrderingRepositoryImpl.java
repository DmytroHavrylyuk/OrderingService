package com.dikzz.repository;

import com.dikzz.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by dikzz on 7/25/16.
 */
@Repository
public class OrderingRepositoryImpl implements OrderingRepository {

    private EntityManager entityManager;

    @Override
    public List<Order> getAll() {
        return entityManager.createQuery("select o from Order o").getResultList();
    }

    @Override
    public void put() {
        Order order = new Order();
        order.setNote("one");
        entityManager.persist(order);

        Order order1 = new Order();
        order1.setNote("two");
        entityManager.persist(order1);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}