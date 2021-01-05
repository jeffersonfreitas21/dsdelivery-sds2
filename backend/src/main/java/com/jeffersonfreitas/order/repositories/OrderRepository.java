package com.jeffersonfreitas.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jeffersonfreitas.order.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findAllByOrderByMoment();
	
	@Query("select distinct o from Order o join fetch o.products where o.status = 0 order by o.moment asc")
	List<Order> findOrderWithProducts();

}
