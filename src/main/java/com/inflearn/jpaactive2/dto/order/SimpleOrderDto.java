package com.inflearn.jpaactive2.dto.order;

import com.inflearn.jpaactive2.domain.Address;
import com.inflearn.jpaactive2.domain.Order;
import com.inflearn.jpaactive2.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class SimpleOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderTime();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
    }
}
