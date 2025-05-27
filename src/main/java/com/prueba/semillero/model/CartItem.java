package com.prueba.semillero.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String productId;
    private int cantidad;

    public CartItem() {
    }

    public CartItem(String productId, int cantidad) {
        this.productId = productId;
        this.cantidad = cantidad;
    }

// Getters y setters

}