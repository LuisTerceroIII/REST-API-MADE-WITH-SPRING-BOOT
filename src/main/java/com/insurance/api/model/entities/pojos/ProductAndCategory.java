package com.insurance.api.model.entities.pojos;

import javax.persistence.Tuple;

public class ProductAndCategory {

        Long product;
        String category;

    public ProductAndCategory(Long product, String category) {
        this.product = product;
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductAndCategory {" +
                "product=" + product +
                ", category='" + category + '\'' +
                '}';
    }
}
