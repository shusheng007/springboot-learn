package top.shusheng007.architecturedemo.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.shusheng007.architecturedemo.application.dto.OrderCreateDto;
import top.shusheng007.architecturedemo.application.dto.ProductItemDto;
import top.shusheng007.architecturedemo.application.service.OrderApplicationService;
import top.shusheng007.architecturedemo.domain.aggregate.Order;
import top.shusheng007.architecturedemo.domain.entity.OrderItem;
import top.shusheng007.architecturedemo.domain.entity.Product;
import top.shusheng007.architecturedemo.domain.external.PaymentGateway;
import top.shusheng007.architecturedemo.domain.external.repository.OrderRepository;
import top.shusheng007.architecturedemo.domain.service.OrderCreateDomainService;
import top.shusheng007.architecturedemo.domain.valueobj.Address;
import top.shusheng007.architecturedemo.domain.valueobj.PaymentDetail;

@RequiredArgsConstructor
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreateDomainService orderCreateDomainService;

    private final PaymentGateway paymentGateway;

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public Order createOrder(OrderCreateDto orderCreateDto) {
        Order order = new Order(1L, "ShuSheng007", new Address("河西区解放南路", "天津", "300000"));
        order.setEventPublisher(eventPublisher);
        for (ProductItemDto item : orderCreateDto.getProducts()) {
            Product product = item.getProduct();

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getProductId());
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(item.getCount());

            order.addItem(orderItem);
        }

        orderCreateDomainService.createOrder(order);

        return orderRepository.save(order);
    }

    @Override
    public Order completeOrder(Long orderId, PaymentDetail paymentDetail) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        boolean isOk = paymentGateway.getPayment(paymentDetail.getPayType()).payOrder(orderId, paymentDetail);
        if (!isOk) {
            throw new IllegalStateException("pay failed");
        }
        order.payOrder();
        order.shipOrder();

        return orderRepository.save(order);
    }
}
