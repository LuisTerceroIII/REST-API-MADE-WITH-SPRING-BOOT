package com.insurance.api.model.entities.pojos.request;

import java.util.Date;

public class MovementPost {
    private Long movementTypeId;
    private Long productId;
    private Integer quantity;
    private Integer priceUnit;
    private String date;
    private Integer total;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getMovementTypeId() {
        return movementTypeId;
    }

    public void setMovementTypeId(Long movementTypeId) {
        this.movementTypeId = movementTypeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Integer priceUnit) {
        this.priceUnit = priceUnit;
    }


    public Integer getTotal() {
        return total;
    }


    public void setTotal(Integer total) {
        this.total = total;
    }


    }


