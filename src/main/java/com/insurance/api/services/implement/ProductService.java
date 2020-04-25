package com.insurance.api.services.implement;

import com.insurance.api.model.entities.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {

    public Product largerStock();
    public Product insertProduct(Product product);
    public Optional<Product> getOneId(Long id);
}
