package top.ss007.architecturedemo.infrastructure.adapter.repository;

import top.ss007.architecturedemo.domain.aggregate.Order;
import top.ss007.architecturedemo.domain.external.repository.OrderRepository;

import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Optional<Order> findById(Long orderId) {
        Order order = new Order();
        order.setId(1L);
        return Optional.of(order);
    }

    @Override
    public Order save(Order order) {
        return null;
    }
}
