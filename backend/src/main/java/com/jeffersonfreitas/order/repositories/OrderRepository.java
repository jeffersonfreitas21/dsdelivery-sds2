package com.jeffersonfreitas.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeffersonfreitas.order.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
