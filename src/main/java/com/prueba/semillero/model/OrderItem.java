package com.prueba.semillero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private String productId;
    private int cantidad;

    public OrderItem() {
    }

    public OrderItem(String productId, int cantidad) {
        this.productId = productId;
        this.cantidad = cantidad;
    }

// Getters y setters
}
