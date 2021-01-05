package com.jeffersonfreitas.order.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeffersonfreitas.order.dto.OrderDTO;
import com.jeffersonfreitas.order.entities.Order;
import com.jeffersonfreitas.order.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> orders = repository.findOrderWithProducts();
		return orders.stream().map(p -> new OrderDTO(p)).collect(Collectors.toList());
	}

}
