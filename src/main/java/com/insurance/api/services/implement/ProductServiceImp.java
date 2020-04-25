package com.insurance.api.services.implement;

import com.insurance.api.model.entities.Product;
import com.insurance.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository jpa;

    @Override
    public Product largerStock() {
        List <Product> products = new ArrayList<>();
        products = jpa.findAll();
        int max = 0;
        Long id = null;
        Product quantityProduct = null;
        for( Product product : products) {
            if(product.getQuantity() > max) {
                max = product.getQuantity();
                id = product.getId();
            }
        }
        return jpa.getOne(id);
    }

    @Override
    public Product insertProduct(Product product) {
        return jpa.save(product);
    }

    @Override
    public Optional<Product> getOneId(Long id) {

        return jpa.findById(id);
    }
}
