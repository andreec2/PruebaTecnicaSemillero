package com.prueba.semillero.service;

import com.prueba.semillero.model.Cart;
import com.prueba.semillero.model.CartItem;
import com.prueba.semillero.model.Order;
import com.prueba.semillero.model.OrderItem;
import com.prueba.semillero.repository.CartRepository;
import com.prueba.semillero.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    // Crear un pedido a partir del carrito cerrado
    public Order createOrderFromCart(String userEmail) {
        Optional<Cart> optionalCart = cartRepository.findByUserEmailAndCerradoFalse(userEmail);

        if (optionalCart.isEmpty()) {
            throw new RuntimeException("No hay carrito activo para el usuario");
        }

        Cart cart = optionalCart.get();

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Marcar carrito como cerrado
        cart.setCerrado(true);
        cartRepository.save(cart);

        // Convertir CartItem → OrderItem
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(i -> new OrderItem(i.getProductId(), i.getCantidad()))
                .collect(Collectors.toList());

        double total = calcularTotal(orderItems); // podrías reemplazar esto si tienes precios

        Order order = new Order(userEmail, orderItems, LocalDateTime.now(), total);

        return orderRepository.save(order);
    }

    // Obtener historial del usuario
    public List<Order> getOrdersByUser(String userEmail) {
        return orderRepository.findByUserEmail(userEmail);
    }

    // Método temporal (sin acceso a precios reales)
    private double calcularTotal(List<OrderItem> items) {
        return items.size() * 100.0; // ⚠️ simulado: cada ítem = $100
    }
}