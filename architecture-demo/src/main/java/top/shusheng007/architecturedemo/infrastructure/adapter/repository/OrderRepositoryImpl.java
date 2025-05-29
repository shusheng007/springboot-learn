package top.shusheng007.architecturedemo.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import top.shusheng007.architecturedemo.domain.aggregate.Order;
import top.shusheng007.architecturedemo.domain.external.repository.OrderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final Map<Long, Order> map = new HashMap<>();

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.of(map.get(orderId));
    }

    @Override
    public Order save(Order order) {
        map.put(order.getId(), order);
        return order;
    }
}
