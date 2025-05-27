package com.prueba.semillero.repository;

import com.prueba.semillero.model.Cart;
import com.prueba.semillero.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserEmailAndCerradoFalse(String userEmail);
}

