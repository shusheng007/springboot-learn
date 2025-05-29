package top.shusheng007.architecturedemo.domain.service;

import top.shusheng007.architecturedemo.domain.aggregate.Order;

public interface OrderCreateDomainService {

    Order createOrder(Order order);
}
