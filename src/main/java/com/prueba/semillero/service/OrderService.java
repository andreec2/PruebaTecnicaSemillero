package com.prueba.semillero.service;

import com.prueba.semillero.model.*;
import com.prueba.semillero.repository.CartRepository;
import com.prueba.semillero.repository.OrderRepository;
import com.prueba.semillero.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    // Crear un pedido a partir del carrito cerrado
    public Order createOrderFromCart(String userEmail) {
        // Buscar el carrito activo del usuario
        Cart cart = cartRepository.findByUserEmailAndCerradoFalse(userEmail)
                .orElseThrow(() -> new RuntimeException("No hay carrito activo para el usuario"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0.0;

        for (CartItem cartItem : cart.getItems()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + cartItem.getProductId()));

            if (cartItem.getCantidad() > product.getStock()) {
                throw new RuntimeException("Stock insuficiente para " + product.getNombre());
            }

            // Disminuir el stock
            product.setStock(product.getStock() - cartItem.getCantidad());
            productRepository.save(product);

            // Crear OrderItem con datos del producto
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setNombre(product.getNombre());
            orderItem.setPrecioUnitario(product.getPrecio());
            orderItem.setCantidad(cartItem.getCantidad());

            total += product.getPrecio() * cartItem.getCantidad();
            orderItems.add(orderItem);
        }

        // Marcar carrito como cerrado
        cart.setCerrado(true);
        cartRepository.save(cart);

        // Crear y guardar el pedido
        Order order = new Order(userEmail, orderItems, LocalDateTime.now(), total);
        return orderRepository.save(order);

    }

    // Obtener historial del usuario
    public List<Order> getOrdersByUser(String userEmail) {
        return orderRepository.findByUserEmail(userEmail);
    }

    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteById(orderId);
    }

    // Método temporal (sin acceso a precios reales)
    private double calcularTotal(List<OrderItem> items) {
        return items.size() * 100.0; // ⚠️ simulado: cada ítem = $100
    }

}