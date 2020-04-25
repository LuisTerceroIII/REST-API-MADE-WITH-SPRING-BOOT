package com.insurance.api.model.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="Product", schema="PUBLIC")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name="brand", nullable = false)
    private String brand;
    @Column(name="name", nullable = false)
    private  String name;
    @Column(name="unity", nullable = false)
    private String unity;
    @Column(name="quantity", nullable = false)
    private int quantity;

    public Product() {}

    public Product(Category category, String brand, String name, String unity, int quantity) {
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.unity = unity;
        this.quantity = quantity;
    }

    public Product(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product { " +
                "id = " + id +
                ", id_category =" + category +
                ", brand = '" + brand + '\'' +
                ", name = '" + name + '\'' +
                ", unity = '" + unity + '\'' +
                ", quantity = " + quantity +
                " }";
    }



}
