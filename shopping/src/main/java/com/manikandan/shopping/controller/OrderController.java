package com.manikandan.shopping.controller;

import com.manikandan.shopping.dto.CreateOrderRequest;
import com.manikandan.shopping.dto.OrderCreated;
import com.manikandan.shopping.entity.Order;
import com.manikandan.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest)
    {
        OrderCreated order = orderService.createOrder(orderRequest);  //all list items are stored at CreateOrderRequest Object and
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/{reference_id}")
    public ResponseEntity<?> getOrder(@PathVariable String reference_id){
        Order order = orderService.getOrder(reference_id);
        return ResponseEntity.ok().body(order);
    }
}
