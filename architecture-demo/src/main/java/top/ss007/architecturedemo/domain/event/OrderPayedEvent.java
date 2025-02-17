package top.ss007.architecturedemo.domain.event;

public class OrderPayedEvent extends OrderEvent {

    public OrderPayedEvent(Long orderId) {
        super(orderId);
    }
}
