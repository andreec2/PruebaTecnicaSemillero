    package com.prueba.semillero.service;

    import com.prueba.semillero.model.Cart;
    import com.prueba.semillero.model.CartItem;
    import com.prueba.semillero.model.Product;
    import com.prueba.semillero.repository.CartRepository;
    import com.prueba.semillero.repository.ProductRepository;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class CartService {
        private final CartRepository cartRepository;

        private final ProductRepository productRepository;

        public CartService(CartRepository cartRepository, ProductRepository productRepository) {
            this.cartRepository = cartRepository;
            this.productRepository = productRepository;
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
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (item.getCantidad() > product.getStock()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + product.getNombre());
            }

            Cart cart = getOrCreateCart(userEmail);
            List<CartItem> items = cart.getItems();

            Optional<CartItem> existente = items.stream()
                    .filter(i -> i.getProductId().equals(item.getProductId()))
                    .findFirst();

            if (existente.isPresent()) {
                int nuevaCantidad = existente.get().getCantidad() + item.getCantidad();
                if (nuevaCantidad > product.getStock()) {
                    throw new RuntimeException("Supera el stock disponible");
                }
                existente.get().setCantidad(nuevaCantidad);
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


