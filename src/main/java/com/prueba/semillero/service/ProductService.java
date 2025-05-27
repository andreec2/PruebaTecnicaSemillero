package com.prueba.semillero.service;

import com.prueba.semillero.model.Product;
import com.prueba.semillero.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(String id) {
        return repo.findById(id);
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

}
