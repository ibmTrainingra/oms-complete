package com.ibm.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.demo.repo.OrderRepository;
import com.ibm.demo.entity.Order;

@Service
public class OrderService {//spring beans
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	RestTemplate getTaxesTemplate;
public String createOrder(Order order) {
	//call getTaxes
	Float tax=getTaxesTemplate.getForObject("http://localhost:8080/getTaxes?price={price}",Float.class,order.getPrice() );
	System.out.println(tax);
	order.setTax(tax);
	Order savedOrder= orderRepository.save(order);
	return savedOrder.getId();
}



public void updateOrder(Order order) {
	orderRepository.save(order);
	
}

public void deleteOrder(String orderId) {
	
	orderRepository.deleteById(orderId);
}


}
