package com.insurance.api.model.entities.pojos.request;

public class CategoryPost {

     String name;

    public CategoryPost() {
    }

    public CategoryPost( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryPost{" +
                ", name='" + name + '\'' +
                '}';
    }
}
