package com.prueba.semillero.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String productId;
    private int cantidad;
    private String nombre;
    private double precioUnitario;

    public CartItem() {
    }

    public CartItem(String productId, int cantidad, String nombre, double precioUnitario) {
        this.productId = productId;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

// Getters y setters

}