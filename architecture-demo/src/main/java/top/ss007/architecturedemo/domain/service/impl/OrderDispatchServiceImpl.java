package top.ss007.architecturedemo.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.ss007.architecturedemo.domain.aggregate.Order;
import top.ss007.architecturedemo.domain.external.repository.OrderRepository;
import top.ss007.architecturedemo.domain.service.OrderDispatchService;

@RequiredArgsConstructor
@Service
public class OrderDispatchServiceImpl implements OrderDispatchService {
    private final OrderRepository orderRepository;

    @Override
    public boolean dispatchOrder(Long orderId) {
        // 获取订单
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.shipOrder();
        orderRepository.save(order);

        return true;
    }


}
