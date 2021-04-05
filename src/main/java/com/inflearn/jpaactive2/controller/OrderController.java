package com.inflearn.jpaactive2.controller;

import com.inflearn.jpaactive2.domain.Order;
import com.inflearn.jpaactive2.dto.order.SimpleOrderDto;
import com.inflearn.jpaactive2.dto.order.SimpleQueryOrderDto;
import com.inflearn.jpaactive2.repository.OrderRepository;
import com.inflearn.jpaactive2.repository.query.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<SimpleQueryOrderDto> ordersV4() {
        return orderQueryRepository.findOrderDTOs();
    }
}
