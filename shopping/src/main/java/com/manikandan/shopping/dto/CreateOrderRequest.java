package com.manikandan.shopping.dto;

import java.util.List;

public class CreateOrderRequest {
    private List<OrderItemDto> items;

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
}
