package top.shusheng007.architecturedemo.domain.aggregate;

import lombok.RequiredArgsConstructor;
import top.shusheng007.architecturedemo.domain.event.OrderEvent;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class RootAggregate<T> {
    private final List<OrderEvent> domainEvents = new ArrayList<>();

    protected void addDomainEvent(OrderEvent event) {
        domainEvents.add(event);
    }

    protected List<OrderEvent> getDomainEvents() {
        return domainEvents;
    }

    protected void clearDomainEvents() {
        domainEvents.clear();
    }


}
