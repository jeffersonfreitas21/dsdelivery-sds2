package com.jeffersonfreitas.order.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeffersonfreitas.order.dto.OrderDTO;
import com.jeffersonfreitas.order.dto.ProductDTO;
import com.jeffersonfreitas.order.entities.Order;
import com.jeffersonfreitas.order.entities.OrderStatus;
import com.jeffersonfreitas.order.entities.Product;
import com.jeffersonfreitas.order.repositories.OrderRepository;
import com.jeffersonfreitas.order.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	@Autowired
	private ProductRepository repositoryProduct;
	
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> orders = repository.findOrderWithProducts();
		return orders.stream().map(p -> new OrderDTO(p)).collect(Collectors.toList());
	}
	
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		for(ProductDTO d : dto.getProducts()) {
			Product prod = repositoryProduct.getOne(d.getId());
			order.getProducts().add(prod);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}

}
