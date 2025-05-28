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
            // 1. Verificar que el producto existe
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // 2. Verificar stock suficiente
            if (item.getCantidad() > product.getStock()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + product.getNombre());
            }

            // 3. Obtener o crear carrito del usuario
            Cart cart = getOrCreateCart(userEmail);
            List<CartItem> items = cart.getItems();

            // 4. Buscar si el producto ya está en el carrito
            Optional<CartItem> existente = items.stream()
                    .filter(i -> i.getProductId().equals(item.getProductId()))
                    .findFirst();

            if (existente.isPresent()) {
                CartItem existenteItem = existente.get();
                int nuevaCantidad = existenteItem.getCantidad() + item.getCantidad();

                if (nuevaCantidad > product.getStock()) {
                    throw new RuntimeException("Supera el stock disponible para: " + product.getNombre());
                }

                existenteItem.setCantidad(nuevaCantidad);
                existenteItem.setNombre(product.getNombre());
                existenteItem.setPrecioUnitario(product.getPrecio()/*nuevaCantidad*/);

            } else {
                CartItem nuevo = new CartItem();
                nuevo.setProductId(product.getId());
                nuevo.setNombre(product.getNombre());
                nuevo.setCantidad(item.getCantidad());
                nuevo.setPrecioUnitario(product.getPrecio());

                items.add(nuevo);
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


