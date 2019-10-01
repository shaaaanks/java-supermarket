package com.nationwide.apprenticeship.service;

import com.nationwide.apprenticeship.database.ProductRepository;
import com.nationwide.apprenticeship.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
