package top.ss007.architecturedemo.domain.service;

import top.ss007.architecturedemo.domain.aggregate.Order;

public interface OrderCreateDomainService {

    Order createOrder(Order order);
}
