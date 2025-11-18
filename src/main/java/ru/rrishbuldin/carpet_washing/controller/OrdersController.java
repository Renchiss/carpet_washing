package ru.rrishbuldin.carpet_washing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrishbuldin.carpet_washing.dto.orders.OrderRequestDto;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private Integer count;
    public ResponseEntity<?> addOrder(OrderRequestDto order) {


        return ResponseEntity.ok(order);
    }
}
