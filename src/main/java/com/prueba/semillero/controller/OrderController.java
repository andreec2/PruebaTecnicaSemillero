package com.prueba.semillero.controller;

import com.prueba.semillero.model.Order;
import com.prueba.semillero.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //Eliminar una orden (para pruebas)
    @DeleteMapping("/{email}/remove/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String email, @PathVariable String orderId) {
        Optional<Order> optionalOrder = orderService.getOrderById(orderId);
        if (optionalOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Order order = optionalOrder.get();
        if (!order.getUserEmail().equals(email)) {
            return ResponseEntity.status(403).body("No tienes permiso para eliminar esta orden.");
        }

        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Orden eliminada exitosamente.");
    }
}