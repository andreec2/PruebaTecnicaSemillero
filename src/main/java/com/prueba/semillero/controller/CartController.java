package com.prueba.semillero.controller;

import com.prueba.semillero.model.Cart;
import com.prueba.semillero.model.CartItem;
import com.prueba.semillero.service.CartService;
import com.prueba.semillero.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    // Obtener el carrito del usuario
    @GetMapping("/{email}")
    public ResponseEntity<Cart> getCart(@PathVariable String email) {
        return ResponseEntity.ok(cartService.getOrCreateCart(email));
    }

    // Agregar un producto al carrito
    @PostMapping("/{email}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable String email, @RequestBody CartItem item) {
        Cart updatedCart = cartService.addProductToCart(email, item);
        return ResponseEntity.ok(updatedCart);
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/{email}/remove/{productId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable String email, @PathVariable String productId) {
        Cart updatedCart = cartService.removeProductFromCart(email, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping("/{email}/checkout")
    public ResponseEntity<String> checkout(@PathVariable String email) {
        try {
            orderService.createOrderFromCart(email);
            return ResponseEntity.ok("Pedido generado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al procesar el pedido: " + e.getMessage());
        }
    }
}