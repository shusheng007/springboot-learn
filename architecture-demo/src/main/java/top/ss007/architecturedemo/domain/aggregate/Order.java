package top.ss007.architecturedemo.domain.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.ss007.architecturedemo.domain.entity.OrderItem;
import top.ss007.architecturedemo.domain.enumerate.OrderStatus;
import top.ss007.architecturedemo.domain.event.OrderConfirmedEvent;
import top.ss007.architecturedemo.domain.event.OrderEvent;
import top.ss007.architecturedemo.domain.event.OrderShippedEvent;
import top.ss007.architecturedemo.domain.valueobj.Address;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    private Long id;
    private String customerId;
    private Address shippingAddress;
    private List<OrderItem> items;
    private OrderStatus status;

    private final List<OrderEvent> domainEvents = new ArrayList<>();

    public Order(String customerId, Address shippingAddress) {
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
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
    }

    public void payOrder() {
        if (this.status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order must be confirmed before payed.");
        }
        this.status = OrderStatus.PAYED;
        addDomainEvent(new OrderShippedEvent(this.id));
    }

    public void shipOrder() {
        if (this.status != OrderStatus.PAYED) {
            throw new IllegalStateException("Order must be payed before shipping.");
        }
        this.status = OrderStatus.SHIPPED;
        addDomainEvent(new OrderShippedEvent(this.id));
    }


    private void addDomainEvent(OrderEvent event) {
        domainEvents.add(event);
    }

    public List<OrderEvent> getDomainEvents() {
        return domainEvents;
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
