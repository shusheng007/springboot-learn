package top.shusheng007.architecturedemo.domain.event;

public class OrderShippedEvent extends OrderEvent {
    public OrderShippedEvent(Long orderId) {
        super(orderId);
    }
}
