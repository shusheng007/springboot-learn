package top.ss007.architecturedemo.domain.external.repository;

import top.ss007.architecturedemo.domain.aggregate.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long orderId);

    Order save(Order order);
}
