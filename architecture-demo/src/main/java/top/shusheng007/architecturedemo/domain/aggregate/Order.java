package top.shusheng007.architecturedemo.domain.aggregate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import top.shusheng007.architecturedemo.domain.entity.OrderItem;
import top.shusheng007.architecturedemo.domain.enumerate.OrderStatus;
import top.shusheng007.architecturedemo.domain.event.OrderConfirmedEvent;
import top.shusheng007.architecturedemo.domain.event.OrderEvent;
import top.shusheng007.architecturedemo.domain.event.OrderPayedEvent;
import top.shusheng007.architecturedemo.domain.event.OrderShippedEvent;
import top.shusheng007.architecturedemo.domain.valueobj.Address;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order extends RootAggregate<OrderEvent> {
    private Long id;
    private String customerId;
    private Address shippingAddress;
    private List<OrderItem> items;
    private OrderStatus status;
    private ApplicationEventPublisher eventPublisher;

    public Order(Long orderId, String customerId, Address shippingAddress) {
        this.id = orderId;
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    public void publishEvents() {
        for (OrderEvent domainEvent : getDomainEvents()) {
            eventPublisher.publishEvent(domainEvent);
        }
        clearDomainEvents();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void confirmOrder() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Order must have at least one item.");
        }
        this.status = OrderStatus.CONFIRMED;
        addDomainEvent(new OrderConfirmedEvent(this.id));
        publishEvents();
    }

    public void payOrder() {
        if (this.status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order must be confirmed before payed.");
        }
        this.status = OrderStatus.PAYED;
        addDomainEvent(new OrderPayedEvent(this.id));
        publishEvents();
    }

    public void shipOrder() {
        if (this.status != OrderStatus.PAYED) {
            throw new IllegalStateException("Order must be payed before shipping.");
        }
        this.status = OrderStatus.SHIPPED;
        addDomainEvent(new OrderShippedEvent(this.id));
        publishEvents();
    }



}
