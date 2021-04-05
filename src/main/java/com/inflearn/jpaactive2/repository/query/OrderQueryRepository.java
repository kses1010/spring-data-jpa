package com.inflearn.jpaactive2.repository.query;

import com.inflearn.jpaactive2.dto.order.OrderItemQueryDTO;
import com.inflearn.jpaactive2.dto.order.OrderQueryDTO;
import com.inflearn.jpaactive2.dto.order.SimpleQueryOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {

    private final EntityManager em;

    public List<SimpleQueryOrderDto> findOrderDTOs() {
        return em.createQuery(
                "select new com.inflearn.jpaactive2.dto.order.SimpleQueryOrderDto" +
                        "(o.id, m.name, o.orderTime, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleQueryOrderDto.class)
                .getResultList();
    }

    public List<OrderQueryDTO> findOrderQueryDTOs() {
        List<OrderQueryDTO> result = findOrders();
        for (OrderQueryDTO orderDTO : result) {
            List<OrderItemQueryDTO> itemQueryDTOs = findOrderItems(orderDTO.getOrderId());
            orderDTO.setOrderItems(itemQueryDTOs);
        }
        return result;
    }

    public List<OrderQueryDTO> findAll() {
        List<OrderQueryDTO> result = findOrders();
        List<Long> orderIds = getOrderIds(result);
        List<OrderItemQueryDTO> orderItemList = findOrderItemList(orderIds);
        Map<Long, List<OrderItemQueryDTO>> itemDTOMaps = orderItemList.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDTO::getOrderId));
        for (OrderQueryDTO orderDTO : result) {
            orderDTO.setOrderItems(itemDTOMaps.get(orderDTO.getOrderId()));
        }
        return result;
    }

    private List<Long> getOrderIds(List<OrderQueryDTO> result) {
        return result.stream()
                .map(OrderQueryDTO::getOrderId)
                .collect(Collectors.toList());
    }

    private List<OrderItemQueryDTO> findOrderItemList(List<Long> orderIds) {
        return em.createQuery(
                "select new com.inflearn.jpaactive2.dto.order.OrderItemQueryDTO" +
                        "(oi.order.id, i.name, oi.orderPrice, oi.orderCount)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id in :orderIds", OrderItemQueryDTO.class)
                .setParameter("orderIds", orderIds)
                .getResultList();
    }

    private List<OrderItemQueryDTO> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new com.inflearn.jpaactive2.dto.order.OrderItemQueryDTO" +
                        "(oi.order.id, i.name, oi.orderPrice, oi.orderCount)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = :orderId", OrderItemQueryDTO.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDTO> findOrders() {
        return em.createQuery(
                "select new com.inflearn.jpaactive2.dto.order.OrderQueryDTO" +
                        "(o.id, m.name, o.orderTime, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDTO.class)
                .getResultList();
    }


}
