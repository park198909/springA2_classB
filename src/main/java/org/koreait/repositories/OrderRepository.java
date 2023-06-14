package org.koreait.repositories;

import org.koreait.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends JpaRepository<Order,Long>, QuerydslPredicateExecutor<Order> {
}
