package com.inflearn.jpaactive2.service;

import com.inflearn.jpaactive2.domain.Delivery;
import com.inflearn.jpaactive2.domain.Member;
import com.inflearn.jpaactive2.domain.Order;
import com.inflearn.jpaactive2.domain.OrderItem;
import com.inflearn.jpaactive2.domain.item.Item;
import com.inflearn.jpaactive2.dto.order.OrderDTO;
import com.inflearn.jpaactive2.repository.ItemRepository;
import com.inflearn.jpaactive2.repository.MemberRepository;
import com.inflearn.jpaactive2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
