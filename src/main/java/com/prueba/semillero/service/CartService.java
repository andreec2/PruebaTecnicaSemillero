    package com.prueba.semillero.service;

    import com.prueba.semillero.model.Cart;
    import com.prueba.semillero.model.CartItem;
    import com.prueba.semillero.repository.CartRepository;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class CartService {
        private final CartRepository cartRepository;

        public CartService(CartRepository cartRepository) {
            this.cartRepository = cartRepository;
        }

        // Obtener el carrito activo del usuario o crear uno nuevo
        public Cart getOrCreateCart(String userEmail) {
            return cartRepository.findByUserEmailAndCerradoFalse(userEmail)
                    .orElseGet(() -> {
                        Cart nuevo = new Cart(userEmail, new ArrayList<>(), false);
                        return cartRepository.save(nuevo);
                    });
        }

        // Agregar producto al carrito
        public Cart addProductToCart(String userEmail, CartItem item) {
            Cart cart = getOrCreateCart(userEmail);
            List<CartItem> items = cart.getItems();

            Optional<CartItem> existente = items.stream()
                    .filter(i -> i.getProductId().equals(item.getProductId()))
                    .findFirst();

            if (existente.isPresent()) {
                existente.get().setCantidad(existente.get().getCantidad() + item.getCantidad());
            } else {
                items.add(new CartItem(item.getProductId(), item.getCantidad()));
            }

            return cartRepository.save(cart);
        }

        // Eliminar producto del carrito
        public Cart removeProductFromCart(String userEmail, String productId) {
            Cart cart = getOrCreateCart(userEmail);
            cart.getItems().removeIf(item -> item.getProductId().equals(productId));
            return cartRepository.save(cart);
        }

        // Finalizar el pedido: cerrar el carrito actual
        public void checkoutCart(String userEmail) {
            Cart cart = getOrCreateCart(userEmail);
            cart.setCerrado(true);
            cartRepository.save(cart);
        }
    }


