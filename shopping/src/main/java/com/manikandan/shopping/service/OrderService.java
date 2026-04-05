package com.manikandan.shopping.service;

import com.manikandan.shopping.dto.CreateOrderRequest;
import com.manikandan.shopping.dto.OrderCreated;
import com.manikandan.shopping.dto.OrderItemDto;
import com.manikandan.shopping.entity.Order;
import com.manikandan.shopping.entity.OrderItem;
import com.manikandan.shopping.entity.Product;
import com.manikandan.shopping.repository.OrderRepository;
import com.manikandan.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderCreated createOrder(CreateOrderRequest orderRequest){
        Order order = new Order();  //Create an Empty Order object.

        order.setStatus("Pending");  //initially set as Pending.
        Double totalItemsAmount = 0D;
        for(OrderItemDto item : orderRequest.getItems())  //All items iterate one by one and each item where set at OrderItem object.
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setImage(item.getImage());
            orderItem.setQuantity(item.getQuantity());

            Product product = productRepository.findById(item.getProductId()).orElseThrow(()-> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            totalItemsAmount += item.getPrice() * item.getQuantity();

            order.getOrderItem().add(orderItem);
        }
        order.setTotalItemsAmount(totalItemsAmount);

        Double taxAmount = 10d;
        order.setTaxAmount(taxAmount);

        Double totalAmount = totalItemsAmount * taxAmount;
        order.setTotalAmount(totalAmount);

        String ref_id = UUID.randomUUID().toString();
        order.setReferenceId(ref_id);

        orderRepository.save(order);

        return new OrderCreated(ref_id);   //Only send the Order referenceId, it may use at filtering the Order searching.
    }

    public Order getOrder(String reference_id){
        return orderRepository.findByReferenceId(reference_id).orElseThrow(()-> new RuntimeException("No order found with reference Id"));
    }
}
