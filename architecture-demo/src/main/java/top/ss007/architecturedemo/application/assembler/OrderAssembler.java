package top.ss007.architecturedemo.application.assembler;

import org.springframework.stereotype.Component;
import top.ss007.architecturedemo.application.dto.OrderCreateDto;
import top.ss007.architecturedemo.application.dto.web.request.OrderCreateRequest;
import top.ss007.architecturedemo.application.dto.web.response.OrderCompleteResponse;
import top.ss007.architecturedemo.application.dto.web.response.OrderCreateResponse;
import top.ss007.architecturedemo.domain.aggregate.Order;

@Component
public class OrderAssembler {

    public OrderCreateDto toOrderCreateDto(OrderCreateRequest request) {
        OrderCreateDto orderCreateDto = new OrderCreateDto();
        orderCreateDto.setProducts(request.getProducts());
        return orderCreateDto;
    }

    public OrderCreateResponse toOrderCreateResponse(Order order) {
        OrderCreateResponse orderCreateResponse = new OrderCreateResponse();
        orderCreateResponse.setOrderId(order.getId());
        return orderCreateResponse;
    }

    public OrderCompleteResponse toOrderCompleteResponse(Order order) {
        OrderCompleteResponse orderCompleteResponse = new OrderCompleteResponse();
        orderCompleteResponse.setOrderId(order.getId());
        return orderCompleteResponse;
    }

}
