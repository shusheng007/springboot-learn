package top.shusheng007.architecturedemo.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import top.shusheng007.architecturedemo.application.service.LogisticsApplicationService;
import top.shusheng007.architecturedemo.domain.event.OrderShippedEvent;

@Slf4j
@Service
public class LogisticsApplicationServiceImpl implements LogisticsApplicationService {

    @EventListener
    public void onOrderShippedEvent(OrderShippedEvent event) {
        //处理物流相关操作
        log.info("订单:{} 发货", event.getOrderId());
    }
}
