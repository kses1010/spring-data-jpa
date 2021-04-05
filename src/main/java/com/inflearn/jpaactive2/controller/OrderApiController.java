package com.inflearn.jpaactive2.controller;

import com.inflearn.jpaactive2.domain.Order;
import com.inflearn.jpaactive2.domain.OrderItem;
import com.inflearn.jpaactive2.dto.member.ResultResponse;
import com.inflearn.jpaactive2.dto.order.OrderDTO;
import com.inflearn.jpaactive2.dto.order.OrderQueryDTO;
import com.inflearn.jpaactive2.repository.OrderRepository;
import com.inflearn.jpaactive2.repository.query.OrderQueryRepository;
import com.inflearn.jpaactive2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final OrderQueryRepository queryRepository;


    @GetMapping("/api/v1/orders")
    public List<Order> orders() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                orderItem.getItem().getName();
            }
        }
        return orders;
    }

    @GetMapping("/api/v2/orders")
    public ResultResponse<List<OrderDTO>> ordersV2() {
        return new ResultResponse<>(orderService.findAll());
    }

    @GetMapping("/api/v3/orders")
    public ResultResponse<List<OrderDTO>> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        return getListResultResponse(orders);
    }

    @GetMapping("/api/v3.1/orders")
    public ResultResponse<List<OrderDTO>> ordersV3Page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithItemPage(offset, limit);
        return getListResultResponse(orders);
    }

    @GetMapping("/api/v4/orders")
    public ResultResponse<List<OrderQueryDTO>> ordersV4() {
        return new ResultResponse<>(queryRepository.findOrderQueryDTOs());
    }

    @GetMapping("/api/v5/orders")
    public ResultResponse<List<OrderQueryDTO>> ordersV5() {
        return new ResultResponse<>(queryRepository.findAll());
    }

    private ResultResponse<List<OrderDTO>> getListResultResponse(List<Order> orders) {
        return new ResultResponse<>(
                orders.stream()
                        .map(OrderDTO::new)
                        .collect(Collectors.toList()));
    }
}
