package com.insurance.api.model.entities.pojos;



public class Quantity {

    int quantity;
    String product;

    public Quantity(int quantity, String product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Quantity() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return " Quantity {" +
                "quantity = " + quantity +
                " } ";
    }
}
