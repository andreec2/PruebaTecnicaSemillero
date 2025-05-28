package com.prueba.semillero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Setter
@Getter
@AllArgsConstructor
public class Order {
    @Id
    private String id;

    private String userEmail;
    private List<OrderItem> items;
    private LocalDateTime fecha;
    private double total;

    public Order() {}

    public Order(String userEmail, List<OrderItem> items, LocalDateTime fecha, double total) {
        this.userEmail = userEmail;
        this.items = items;
        this.fecha = fecha;
        this.total = total;
    }

// Getters y setters

}