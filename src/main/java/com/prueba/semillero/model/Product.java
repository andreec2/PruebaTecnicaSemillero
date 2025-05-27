package com.prueba.semillero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@AllArgsConstructor

public class Product {
    @Id
    private String id;

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public Product() {}

    public Product(String nombre, String descripcion, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

// Getters y Setters
// ...

}
