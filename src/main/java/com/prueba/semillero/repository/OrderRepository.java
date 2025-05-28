package com.prueba.semillero.repository;

import com.prueba.semillero.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserEmail(String userEmail);
}