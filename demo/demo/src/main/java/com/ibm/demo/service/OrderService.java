package com.ibm.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ibm.demo.repo.OrderRepository;
import com.ibm.demo.entity.Order;

@Service
public class OrderService {// spring beans classes with special annotation
	@Autowired //dependency of repository class
	OrderRepository orderRepository;
	@Autowired
	RestTemplate getTaxesTemplate;
    @Transactional
	public String createOrder(Order order) {
		// call getTaxes
		//Float tax = getTaxesTemplate.getForObject("http://localhost:8080/getTaxes?price={price}", Float.class,
				//order.getPrice());
		//System.out.println(tax);
		//order.setTax(tax);
		Order savedOrder = orderRepository.save(order);
		//if(order!=null)
		//throw new RuntimeException();
		return savedOrder.getId();
	}

	public void updateOrder(Order order) {
		orderRepository.save(order);

	}

	public void deleteOrder(String orderId) {
          orderRepository.deleteById(orderId);
	}

	
	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

}
