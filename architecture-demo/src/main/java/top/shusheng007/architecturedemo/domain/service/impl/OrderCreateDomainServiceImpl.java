package top.shusheng007.architecturedemo.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.shusheng007.architecturedemo.domain.aggregate.Inventory;
import top.shusheng007.architecturedemo.domain.aggregate.Order;
import top.shusheng007.architecturedemo.domain.entity.OrderItem;
import top.shusheng007.architecturedemo.domain.external.repository.InventoryRepository;
import top.shusheng007.architecturedemo.domain.external.repository.OrderRepository;
import top.shusheng007.architecturedemo.domain.service.OrderCreateDomainService;

@RequiredArgsConstructor
@Service
public class OrderCreateDomainServiceImpl implements OrderCreateDomainService {
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;


    @Override
    public Order createOrder(Order order) {
        for (OrderItem orderItem : order.getItems()) {
            //协调另一个聚合根扣减库存
            Inventory inventory = inventoryRepository.findById(orderItem.getProductId()).orElseThrow();
            inventory.decreaseStock(orderItem.getQuantity());
        }
        order.confirmOrder();
        return order;
    }


}
