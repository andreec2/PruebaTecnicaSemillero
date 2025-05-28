package com.prueba.semillero.controller;

import com.prueba.semillero.model.Order;
import com.prueba.semillero.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Confirmar compra → genera un pedido
    @PostMapping("/{email}/create")
    public ResponseEntity<Order> createOrder(@PathVariable String email) {
        Order order = orderService.createOrderFromCart(email);
        return ResponseEntity.ok(order);
    }

    // Obtener historial de pedidos de un usuario
    @GetMapping("/{email}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable String email) {
        return ResponseEntity.ok(orderService.getOrdersByUser(email));
    }
}