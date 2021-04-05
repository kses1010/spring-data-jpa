package com.inflearn.jpaactive2.dto.order;

import com.inflearn.jpaactive2.domain.Address;
import com.inflearn.jpaactive2.domain.Order;
import com.inflearn.jpaactive2.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDTO> orderItems;

    public OrderDTO(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderTime();
        orderStatus = order.getStatus();
        address = order.getMember().getAddress();
        orderItems = order.getOrderItems().stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());

    }
}
