package com.insurance.api.hateoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.insurance.api.model.entities.Category;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@JsonRootName(value = "product")
@Relation(collectionRelation = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModel extends RepresentationModel<ProductModel> {
    //MODELO que almacena Links de Entidad Product JPA
    private Long id;
    private String name;
    private String brand;
    private String unity;
    private int quantity;
    private String nameCategory;
    private Long idCategory;
    private Category category;

public ProductModel() {
    super();
}

    public ProductModel(Long id, String name, String brand, String unity, int quantity, Category category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.unity = unity;
        this.quantity = quantity;
        this.category = category;
    }

    public ProductModel(Link initialLink, Long id, String name, String brand, String unity, int quantity, Category category) {
        super(initialLink);
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.unity = unity;
        this.quantity = quantity;
        this.category = category;
    }

    public ProductModel(List<Link> initialLinks, Long id, String name, String brand, String unity, int quantity, Category category) {
        super(initialLinks);
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.unity = unity;
        this.quantity = quantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void setIdCategory(Long id) {
        this.idCategory = id;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", unity='" + unity + '\'' +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}
