package ru.rrishbuldin.carpet_washing.modules.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderCreateRequestDto;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderDto;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderItemsBatchDto;
import ru.rrishbuldin.carpet_washing.modules.order.entity.OrderItems;
import ru.rrishbuldin.carpet_washing.modules.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @PostMapping("/create/order")
    @PreAuthorize("hasPermission(null, 'CREATE_ORDER')")
    public ResponseEntity<?> addOrder(@RequestBody OrderCreateRequestDto order) {
        OrderDto orderDto = orderService.createOrder(order);
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping("/create/{orderId}/items")
    @PreAuthorize("hasPermission(null , 'EDIT_ORDER')")
    public ResponseEntity<?> addOrderItems(
            @PathVariable Long orderId,
            @RequestBody @Valid OrderItemsBatchDto orderItems) {

        List<OrderItems> orderItems1 = orderService.addItemsToOrder(orderId, orderItems);
        return ResponseEntity.ok(orderItems1);
    }

}
