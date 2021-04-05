package com.inflearn.jpaactive2.dto.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemQueryDTO {

    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int orderCount;

    public OrderItemQueryDTO(Long orderId, String itemName, int orderPrice, int orderCount) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
    }
}
