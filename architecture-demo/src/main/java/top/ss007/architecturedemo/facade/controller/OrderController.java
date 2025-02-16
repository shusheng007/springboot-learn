package top.ss007.architecturedemo.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.ss007.architecturedemo.application.assembler.OrderAssembler;
import top.ss007.architecturedemo.application.dto.OrderCreateDto;
import top.ss007.architecturedemo.application.dto.web.request.OrderCompleteRequest;
import top.ss007.architecturedemo.application.dto.web.request.OrderCreateRequest;
import top.ss007.architecturedemo.application.dto.web.response.OrderCompleteResponse;
import top.ss007.architecturedemo.application.dto.web.response.OrderCreateResponse;
import top.ss007.architecturedemo.application.service.OrderApplicationService;
import top.ss007.architecturedemo.domain.aggregate.Order;
import top.ss007.architecturedemo.domain.enumerate.PayMethod;
import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

      private final OrderApplicationService orderApplicationService;

      private final OrderAssembler orderAssembler;

      @PostMapping("/")
      public ResponseEntity<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest request) {
            OrderCreateDto orderCreateDto = orderAssembler.toOrderCreateDto(request);
            Order order = orderApplicationService.createOrder(orderCreateDto);

            return ResponseEntity.ok(orderAssembler.toOrderCreateResponse(order));
      }

      @PatchMapping("/{id}")
      public ResponseEntity<OrderCompleteResponse> completeOrder(@PathVariable("id") Long orderId, @RequestBody OrderCompleteRequest request) {
            PaymentDetail paymentDetail = new PaymentDetail(String.valueOf(orderId), PayMethod.fromName(request.getPayType()));
            Order order = orderApplicationService.completeOrder(orderId, paymentDetail);

            return ResponseEntity.ok(orderAssembler.toOrderCompleteResponse(order));
      }
}
