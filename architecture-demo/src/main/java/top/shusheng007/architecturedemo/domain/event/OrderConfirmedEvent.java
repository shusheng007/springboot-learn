package top.shusheng007.architecturedemo.domain.event;


public class OrderConfirmedEvent extends OrderEvent {
    public OrderConfirmedEvent(Long orderId) {
        super(orderId);
    }
}
