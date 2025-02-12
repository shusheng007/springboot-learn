package top.ss007.architecturedemo.domain.event;


public class OrderConfirmedEvent extends OrderEvent {
    public OrderConfirmedEvent(Long orderId) {
        super(orderId);
    }
}
