package com.prueba.semillero.controller;

import com.prueba.semillero.model.Cart;
import com.prueba.semillero.model.CartItem;
import com.prueba.semillero.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
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

    // Finalizar pedido (opcional)
    @PostMapping("/{email}/checkout")
    public ResponseEntity<String> checkout(@PathVariable String email) {
        cartService.checkoutCart(email);
        return ResponseEntity.ok("Pedido procesado exitosamente.");
    }
}