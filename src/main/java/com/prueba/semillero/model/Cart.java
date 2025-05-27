package com.prueba.semillero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "carts")
@Getter
@Setter
@AllArgsConstructor
public class Cart {
    @Id
    private String id;
    private String userEmail;
    private List<CartItem> items;
    private boolean cerrado;

    public Cart() {}

    public Cart(String userEmail, List<CartItem> items, boolean cerrado) {
        this.userEmail = userEmail;
        this.items = items;
        this.cerrado = cerrado;
    }

// Getters y setters

}