package top.shusheng007.architecturedemo.domain.event;

public abstract class OrderEvent {
    private final Long orderId;

    protected OrderEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
